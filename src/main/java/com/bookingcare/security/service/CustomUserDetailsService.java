package com.bookingcare.security.service;

import com.bookingcare.model.entity.User;
import com.bookingcare.repository.UserRepository;
import com.bookingcare.security.model.CustomUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("Email not found");
        }
        return CustomUserDetails.build(user);
    }
}
