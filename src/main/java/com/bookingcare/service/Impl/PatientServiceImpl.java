package com.bookingcare.service.Impl;

import com.bookingcare.common.ApiResponse;
import com.bookingcare.exception.BaseException;
import com.bookingcare.model.dto.BookingDTO;
import com.bookingcare.model.entity.Booking;
import com.bookingcare.repository.BookingRepository;
import com.bookingcare.security.entities.User;
import com.bookingcare.security.service.UserService;
import com.bookingcare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    UserService userService;
    @Autowired
    BookingRepository bookingRepository;

    public ApiResponse bookAppointment(BookingDTO requestDTO) {
        try {
            if (requestDTO == null) {
                return new ApiResponse(1, "Missing parameters", null);
            }

            // Kiểm tra xem đã có lịch hẹn hay chưa
            Booking databookcheck = bookingRepository.findByStatusIdAndDoctorIdAndDateAndTimeType("S2", requestDTO.getDoctorId(), requestDTO.getDate(), requestDTO.getTimeType());
            if (databookcheck != null) {
                return new ApiResponse(1, "This doctor already has an appointment at this time", null);
            } else {
                String token = UUID.randomUUID().toString();

                // Tìm kiếm hoặc tạo mới người dùng
                User user = userService.findOrCreateUser(requestDTO);

                // Tạo mới đối tượng Booking
                Booking booking = new Booking();
                booking.setStatusId("S1");
                booking.setDoctorId(requestDTO.getDoctorId());
                booking.setPatientId(user.getId());
                booking.setDate(requestDTO.getDate());
                booking.setTimeType(requestDTO.getTimeType());
                booking.setToken(token);
                Date currentDate = new Date();
                booking.setCreatedAt(currentDate);
                booking.setUpdatedAt(currentDate);
                bookingRepository.save(booking);

                // Gửi email
//            emailService.sendSimpleEmail(requestDTO.getEmail(), requestDTO.getFullName(), // Các thông tin còn lại);

                return new ApiResponse(0, "Patient information saved successfully!", null);
            }

        } catch (Exception e) {
            throw new BaseException(1, "An error occurred during request processing.");
        }
    }
}
