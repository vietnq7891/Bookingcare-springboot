package com.bookingcare.api;

import com.bookingcare.common.ApiResponse;
import com.bookingcare.exception.BaseException;
import com.bookingcare.model.entity.DoctorInfor;
import com.bookingcare.security.entities.User;
import com.bookingcare.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
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
            return new ApiResponse(-1, "Error from server...",null);
        }
    }

}
