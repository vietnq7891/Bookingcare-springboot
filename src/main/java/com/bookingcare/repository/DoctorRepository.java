package com.bookingcare.repository;

import com.bookingcare.model.entity.DoctorInfor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorInfor, Long> {

}
