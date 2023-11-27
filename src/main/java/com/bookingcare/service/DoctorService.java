package com.bookingcare.service;

import com.bookingcare.common.ApiResponse;
import com.bookingcare.common.ValidationResult;
import com.bookingcare.model.entity.DoctorInfor;
import com.bookingcare.security.entities.User;

import java.util.List;

public interface DoctorService {
    ApiResponse getTopDoctorHome(int limit);
    ApiResponse<List<User>> getAllDoctors();
//    ValidationResult checkRequiredFields(DoctorInfor doctorInfor);
//    ApiResponse<String> saveDetailInforDoctor(DoctorInfor inputData);

}
