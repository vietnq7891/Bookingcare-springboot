package com.bookingcare.service;

import com.bookingcare.common.ApiResponse;
import com.bookingcare.model.dto.BookingDTO;

public interface PatientService {
    ApiResponse bookAppointment(BookingDTO requestDTO);
}
