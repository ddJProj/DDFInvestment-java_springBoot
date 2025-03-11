package com.ddfinv.core.entity;

import com.ddfinv.core.entity.enums.Role;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="useraccount")
public class UserAccount {
    
    @Column(name="userid")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * The email address used for an account. Must be unique and not blank.
     */
    private String email;

    /**
     * The hashed password for the account. Must be greater than 8 characters, (? and contain...)
     */
    private String hashedPassword;

    /**
     * The first name of the user
     */
    private String firstName;

    /**
     * The last name of the user
     */
    private String lastName;
    
    /**
     * The role that this UserAccount instance holds
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * The set of permissions that this UserAccount holds, based on their role
     */
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "permission", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions = new HashSet<>();

    /**
     * Default no parameter constructor for a UserAccount instance. Automatically sets user role to guest (limited permissions).
     * 
     */
    public UserAccount(){
        this.role = Role.guest;
    }

    /**
     * New account creation constructor for a UserAccount instance. Automatically sets user role to guest (limited permissions).
     * 
     */
    public UserAccount(String email, String password, String firstName, String lastName){
        this.email = email;
        this.hashedPassword = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = Role.guest; 
    }



    /**
     * Getter for the email attribute of an useraccount instance
     * @return String email - the email value of an account
     */
    public String getEmail(){
       return this.email;
    }
    /**
     * Setter for the email attribute of an useraccount instance
     */
    public void setEmail(String newEmail){
        this.email = newEmail;
    }
    /**
     * Getter for the firstName attribute of a useraccount instance
     * @return String firstName - the firstName value of an account
     */
    public String getFirstName(){
       return this.firstName;
    }

    /**
     * Setter for the firstName attribute of an useraccount instance
     */
    public void setFirstName(String newFirstName){
        this.firstName = newFirstName;
    }

    /**
     * Getter for the lastName attribute of a useraccount instance
     * @return String lastName - the lastName value of an account
     */
    public String getLastName(){
       return this.lastName;
    }

    /**
     * Setter for the lastName attribute of an useraccount instance
     */
    public void setLastName(String newLastName){
        this.lastName = newLastName;
    }

    /**
     * Getter for the role attribute of a useraccount instance
     * @return Role role - the role value of an account
     */
    public Role getRole(){
       return this.role;
    }

    /**
     * Setter for the Role attribute of an useraccount instance
     */
    public void setRole(Role newRole){
        this.role = newRole;
    }

    /**
     * Getter for the role attribute of a useraccount instance
     * @return Role role - the role value of an account
     */
    public Set<Permission> getPermissions(){
       return this.permissions;
    }

    /**
     * Setter for the Role attribute of an useraccount instance
     */
    public void setPermissions(Set<Permission> permissions){
        this.permissions = permissions;
    }


    /**
     * Getter for the stored hashed password attribute of a useraccount instance
     * @return String hashedPassword - the stored hashedPassword of an account
     */
    public String getHashedPassword() {
        return this.hashedPassword;
    }
  
    /**
     * Setter for the stored hashed password attribute of an useraccount instance
     */  
    public void setHashedPassword(String password) {
        this.hashedPassword = password;
    }
}
