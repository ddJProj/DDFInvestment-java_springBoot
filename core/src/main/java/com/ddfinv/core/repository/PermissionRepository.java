package com.ddfinv.core.repository;

import com.ddfinv.core.entity.Permission;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{

    /**
     * Locate/retrieve a permission by it's name
     * 
     * @param name
     * @return Optional permission, returned if found
     */
    Optional<Permission> findByName(String name);

    /**
     * Determines if a permission already exists 
     * 
     * @param name
     * @return boolean true if match found, else false
     */
    boolean existsByName(String name);

}
