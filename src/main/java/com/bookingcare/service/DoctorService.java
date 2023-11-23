package com.bookingcare.service;

import com.bookingcare.common.ApiResponse;

public interface DoctorService {
    ApiResponse getTopDoctorHome(int limit);
}
