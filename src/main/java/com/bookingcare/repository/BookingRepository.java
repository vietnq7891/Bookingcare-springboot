package com.bookingcare.repository;

import com.bookingcare.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findByStatusIdAndDoctorIdAndDateAndTimeType(String statusId, Integer doctorId, String date, String timeType);
}
