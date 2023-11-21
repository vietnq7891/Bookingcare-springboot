package com.bookingcare.security.repo;

import com.bookingcare.model.entity.Allcode;
import com.bookingcare.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    List<Allcode> findByPositionData_Type(String type);
}
