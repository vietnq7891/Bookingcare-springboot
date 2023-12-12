package com.bookingcare.api;

import com.bookingcare.common.ApiResponse;
import com.bookingcare.model.dto.BookingDTO;
import com.bookingcare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PatientController {
    @Autowired
    PatientService patientService;
    @PostMapping("/patient-book-appointment")
    public ResponseEntity<ApiResponse> postBookAppointment(@RequestBody BookingDTO requestDTO) {
        try {
            ApiResponse infor = patientService.bookAppointment(requestDTO);
            return new ResponseEntity<>(infor, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return new ResponseEntity<>(new ApiResponse(-1, "Error from the server", null), HttpStatus.OK);
        }
    }
}
