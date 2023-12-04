package com.bookingcare.service;

import com.bookingcare.common.ApiResponse;
import com.bookingcare.model.dto.*;
import com.bookingcare.model.entity.DoctorInfor;
import com.bookingcare.model.entity.Schedule;
import com.bookingcare.security.entities.User;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    ApiResponse getTopDoctorHome(int limit);
    ApiResponse<List<User>> getAllDoctors();
    ApiResponse saveDetailInforDoctor(DoctorDTO doctorDTO);
    ApiResponse<User> getDetailDoctorById(Integer inputId);

    ApiResponse<Object> bulkCreateSchedule(ScheduleItem request);
    ApiResponse<List<Schedule>> getScheduleByDate(Integer doctorId, String date);
    ApiResponse<DoctorInfor> getExtraInforDoctorById(Integer idInput);
    ApiResponse<Object> getProfileDoctorById(Integer inputId);

}
