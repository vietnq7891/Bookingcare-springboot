package com.bookingcare.service;

import com.bookingcare.common.ApiResponse;
import com.bookingcare.common.ValidationResult;
import com.bookingcare.model.dto.DoctorDTO;
import com.bookingcare.security.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DoctorService {
    ApiResponse getTopDoctorHome(int limit);
    ApiResponse<List<User>> getAllDoctors();
    ApiResponse saveDetailInforDoctor(DoctorDTO doctorDTO);


//    ValidationResult checkRequiredFields(DoctorInfor doctorInfor);
//    ApiResponse<String> saveDetailInforDoctor(DoctorInfor inputData);

}
