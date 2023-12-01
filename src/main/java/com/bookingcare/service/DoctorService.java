package com.bookingcare.service;

import com.bookingcare.common.ApiResponse;
import com.bookingcare.model.dto.DoctorDTO;
import com.bookingcare.security.entities.User;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    ApiResponse getTopDoctorHome(int limit);
    ApiResponse<List<User>> getAllDoctors();
    ApiResponse saveDetailInforDoctor(DoctorDTO doctorDTO);
    Optional<User> getDetailDoctorById(Integer inputId);


//    ValidationResult checkRequiredFields(DoctorInfor doctorInfor);
//    ApiResponse<String> saveDetailInforDoctor(DoctorInfor inputData);

}
