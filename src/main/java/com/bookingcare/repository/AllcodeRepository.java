package com.bookingcare.repository;

import com.bookingcare.model.entity.Allcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AllcodeRepository extends JpaRepository<Allcode, Long> {
    List<Allcode> findByType(String type);
}
