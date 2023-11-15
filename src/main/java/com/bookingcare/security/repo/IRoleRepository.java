package com.bookingcare.security.repo;


import com.bookingcare.security.entities.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
