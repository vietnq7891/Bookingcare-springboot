package com.bookingcare.service.impl;

import com.bookingcare.model.entity.User;
import com.bookingcare.security.repository.UserRepository;
import com.bookingcare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public User handleUserLogin(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String createNewUser(User user) {
        userRepository.save(user);
        return "User created successfully";
    }

    public String updateUserData(User user) {
        userRepository.save(user);
        return "User data updated successfully";
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }
}
