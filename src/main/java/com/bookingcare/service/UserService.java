package com.bookingcare.service;

import com.bookingcare.model.dto.UserRequest;
import com.bookingcare.model.dto.UserResponse;
import com.bookingcare.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    String hashUserPassword(String password);
    UserResponse handleUserLogin(String email, String password);
    boolean checkUserEmail(String userEmail);
    UserResponse getAllUsers(String userId);
    UserResponse createNewUser(UserRequest data);
    UserResponse deleteUser(Long userId);
    UserResponse updateUserData(UserRequest data);
    UserResponse getAllCodeService(String typeInput);
}
