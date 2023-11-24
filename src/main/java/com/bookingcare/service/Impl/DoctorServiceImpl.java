package com.bookingcare.service.Impl;

import com.bookingcare.common.ApiResponse;
import com.bookingcare.exception.BaseException;
import com.bookingcare.repository.DoctorRepository;
import com.bookingcare.repository.SpecialtyRepository;
import com.bookingcare.security.entities.User;
import com.bookingcare.security.repo.IUserRepository;
import com.bookingcare.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    SpecialtyRepository specialtyRepository;
    @Override
    public ApiResponse getTopDoctorHome(int limit) {
        try {
            List<User> users = userRepository.findTopDoctors(limit);

            // You can process attributes, filters, and format data here if needed

            return new ApiResponse(0, "Success", users);
        } catch (Exception e) {
            // Handle exceptions
            return new ApiResponse(-1, "Error from server...", null);
        }
    }

}
