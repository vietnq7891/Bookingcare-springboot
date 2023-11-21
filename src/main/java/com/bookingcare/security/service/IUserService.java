package com.bookingcare.security.service;
import com.bookingcare.common.ApiResponse;
import com.bookingcare.model.entity.Allcode;
import com.bookingcare.service.IGeneralService;

import com.bookingcare.security.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    Optional<User> findByUsername(String username);
    ApiResponse<User> save(User user);
    ApiResponse<User> updateUserData(User data);

    ApiResponse<List<Allcode>> getAllCode(String typeInput);
}
