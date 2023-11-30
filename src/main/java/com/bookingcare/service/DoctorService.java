package com.bookingcare.service;

import com.bookingcare.common.ApiResponse;
import com.bookingcare.common.ValidationResult;
import com.bookingcare.model.dto.DoctorDTO;

public interface DoctorService {
    ApiResponse getTopDoctorHome(int limit);
//    ApiResponse saveDetailInforDoctor(DoctorDTO doctorDTO);


//    ValidationResult checkRequiredFields(DoctorInfor doctorInfor);
//    ApiResponse<String> saveDetailInforDoctor(DoctorInfor inputData);

}
