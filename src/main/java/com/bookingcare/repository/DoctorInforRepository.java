package com.bookingcare.repository;

import com.bookingcare.model.entity.DoctorInfor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorInforRepository extends JpaRepository<DoctorInfor, Integer> {
    Optional<DoctorInfor> findByDoctorId(Integer doctorId);
}
