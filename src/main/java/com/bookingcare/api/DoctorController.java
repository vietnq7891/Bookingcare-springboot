package com.bookingcare.api;

import com.bookingcare.common.ApiResponse;
import com.bookingcare.exception.BaseException;
import com.bookingcare.model.dto.DoctorDTO;
import com.bookingcare.model.entity.DoctorInfor;
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




}
