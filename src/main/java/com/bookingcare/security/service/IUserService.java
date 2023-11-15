package com.bookingcare.security.service;
import com.bookingcare.service.IGeneralService;

import com.bookingcare.security.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    Optional<User> findByUsername(String username);
}
