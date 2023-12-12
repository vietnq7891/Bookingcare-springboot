package com.bookingcare.security.repo;

import com.bookingcare.model.entity.Allcode;
import com.bookingcare.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    @Query("SELECT u FROM User u WHERE u.roleId = 'R2' ORDER BY u.createdAt DESC")
    List<User> findTopDoctors(@Param("limit") int limit);
    List<User> findAllByRoleId(String roleId);
    User findByEmail(String email);
}
