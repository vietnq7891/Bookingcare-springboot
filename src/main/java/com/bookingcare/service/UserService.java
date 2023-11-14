package com.bookingcare.service;

import com.bookingcare.model.entity.User;

import java.util.List;

public interface UserService {
    public User handleUserLogin(String email, String password);
    public List<User> getAllUsers();
    public String createNewUser(User user);
    public String updateUserData(User user);
    public String deleteUser(Long id);
}
