package com.bookingcare.service;

import com.bookingcare.common.ApiResponse;
import com.bookingcare.security.entities.User;

import java.util.List;

public interface DoctorService {
    ApiResponse getTopDoctorHome(int limit);

}
