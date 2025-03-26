import { Permissions } from './../types/auth.types';
import { Login } from './../components/navigation/Navigate';
/* eslint-disable @typescript-eslint/no-explicit-any */
// src/hooks/useAuth.ts
import { useState } from 'react';
import { Route, Routes, useNavigate } from 'react-router-dom';
import { apiService } from '../services/api.service';
import { authUtils } from '../utils/auth.utils';
import { UserRole } from '../types/auth.types';
import { ROUTES } from "../constants/router.constants";
import { jwtDecode } from 'jwt-decode';

export const useAuth = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(
    authUtils.isAuthenticated()
  );
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();

  const login = async (email: string, password: string) => {
    setLoading(true);
    setError(null);


    try {
      const response = await apiService.auth.login(email, password);
      // Store the token and user info
      const authData = response.data;

      // attempt to map the strings of permissions to Permission enum values
      const mappedPermissions = authData.permissions ? authData.permissions.map((permString: string) =>{
        return Permissions[permString as keyof typeof Permissions] || null;
      }).filter(Boolean) : [];


      // TODO: add logic to extract role from JWT payload?
      const userData = {
        id: authData.id || 0, // if one isnt provided automatically by db/backend
        email: authData.email || email,
        firstName: authData.firstName || '',
        lastName: authData.lastName ||  '',
        role: authData.role || UserRole.Guest, // fallback to userRole Guest / limited permission
        permissions: mappedPermissions
      };

      authUtils.setAuthData(authData.token, userData);
      setIsAuthenticated(true);

      // redirect based on role
      switch(userData.role){
        case UserRole.Admin:
          navigate(ROUTES.ADMIN);
          break;
        case UserRole.Employee:
          navigate(ROUTES.EMPLOYEE);
          break;
        case UserRole.Client:
          navigate(ROUTES.CLIENT);
          break;
        default:
          navigate(ROUTES.DASHBOARD);
      }

      return true;

    } catch (err: any) {
      console.error('Error logging in: ', err);  // added to troubleshoot
      setError(
        err.response?.data?.message ||
          'Login failed. Please check your credentials.'
      );
      return false;
    } finally {
      setLoading(false);
    }
  };

  const register = async (
    firstName: string,
    lastName: string,
    email: string,
    password: string
  ) => {
    setLoading(true);
    setError(null);

    try {
      await apiService.auth.register(firstName, lastName, email, password);
      navigate('/login', {
        state: { message: 'Registration successful! Please log in.' },
      });
      return true;
    } catch (err: any) {
      setError(
        err.response?.data?.message || 'Registration failed. Please try again.'
      );
      return false;
    } finally {
      setLoading(false);
    }
  };

  const logout = () => {
    authUtils.clearAuthData();
    setIsAuthenticated(false);
    navigate(ROUTES.LOGIN);
  };

  return {
    isAuthenticated,
    loading,
    error,
    login,
    register,
    logout,
  };
};
