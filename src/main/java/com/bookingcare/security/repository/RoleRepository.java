package com.bookingcare.security.repository;

import com.bookingcare.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role>  findByName(String roleName);
}
