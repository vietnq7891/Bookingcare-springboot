package com.bookingcare.api;

import com.bookingcare.common.ApiResponse;
import com.bookingcare.exception.BaseException;
import com.bookingcare.model.dto.DoctorDTO;
import com.bookingcare.model.dto.ScheduleDTO;
import com.bookingcare.model.dto.ScheduleItem;
import com.bookingcare.model.dto.UserDTO;
import com.bookingcare.model.entity.DoctorInfor;
import com.bookingcare.model.entity.Schedule;
import com.bookingcare.security.entities.User;
import com.bookingcare.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @GetMapping("/top-doctor-home")
    public ApiResponse getTopDoctorHome(@RequestParam(defaultValue = "10") int limit) {
        try {
            return doctorService.getTopDoctorHome(limit);
        } catch (Exception e) {
            // Handle exceptions
            return new ApiResponse(-1, "Error from server...", null);
        }
    }

    @PostMapping("/save-infor-doctors")
    public ResponseEntity<ApiResponse<Void>> saveDoctorData(@RequestBody DoctorDTO doctorDTO) {
        try {
            ApiResponse<Void> response = doctorService.saveDetailInforDoctor(doctorDTO);
            return ResponseEntity.ok(response);
        } catch (BaseException e) {
            // Xử lý lỗi và trả về ApiResponse với thông điệp lỗi
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(-1, "Error from the server", null));
        }
    }

    @GetMapping("/get-all-doctors")
    public ResponseEntity<ApiResponse<List<User>>> getAllDoctors() {
        try {
            ApiResponse<List<User>> response = doctorService.getAllDoctors();
            return ResponseEntity.ok(response);
        } catch (BaseException e) {
            ApiResponse<List<User>> errorResponse = new ApiResponse<>(e.getErrorMessage().getCode(), e.getErrorMessage().getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/get-detail-doctor-by-id")
    public ResponseEntity<ApiResponse<User>> getDoctorDetailById(@RequestParam Integer id) {
        try {
            ApiResponse<User> response = doctorService.getDetailDoctorById(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BaseException e) {
            ApiResponse<User> errorResponse = new ApiResponse<>(e.getErrorMessage().getCode(), e.getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/bulk-create-schedule")
    public ResponseEntity<ApiResponse<Object>> bulkCreateSchedule(@RequestBody ScheduleItem request) {
        try {
            doctorService.bulkCreateSchedule(request);
            ApiResponse<Object> apiResponse = new ApiResponse<>(0, "Success", null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (BaseException e) {
            ApiResponse<Object> apiResponse = new ApiResponse<>(500, e.getMessage(), null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
    }
    @GetMapping("/get-schedule-doctor-by-date")
    public ResponseEntity<ApiResponse<?>> getScheduleByDate(@RequestParam Integer doctorId, @RequestParam String date) {
        try {
            ApiResponse<List<Schedule>> response = doctorService.getScheduleByDate(doctorId, date);
            return ResponseEntity.ok(response);
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(500, e.getMessage(), null));
        }
    }

    @GetMapping("/get-extra-infor-doctor-by-id")
    public ResponseEntity<ApiResponse<DoctorInfor>> getExtraInforDoctorById(@RequestParam Integer doctorId) {
        try {
            ApiResponse<DoctorInfor> infor = doctorService.getExtraInforDoctorById(doctorId);
            return ResponseEntity.status(200).body(infor);
        } catch (Exception e) {
            return ResponseEntity.status(200).body(new ApiResponse<>(-1, "Error from the server", null));
        }
    }


    @GetMapping("/get-profile-doctor-by-id")
    public ResponseEntity<ApiResponse<Object>> getProfileDoctorById(@RequestParam Integer doctorId) {
        try {
            ApiResponse<Object> infor = doctorService.getProfileDoctorById(doctorId);
            return ResponseEntity.ok(infor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(-1, "Error from the server", null));
        }
    }

}
