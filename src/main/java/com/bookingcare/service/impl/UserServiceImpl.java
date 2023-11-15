package com.bookingcare.service.impl;

import com.bookingcare.model.dto.UserRequest;
import com.bookingcare.model.dto.UserResponse;
import com.bookingcare.model.entity.Allcodes;
import com.bookingcare.model.entity.User;
import com.bookingcare.repository.UserRepository;
import com.bookingcare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String hashUserPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public UserResponse handleUserLogin(String email, String password) {
        UserResponse userData = new UserResponse();
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (passwordEncoder.matches(password, user.getPassword())) {
                userData.setErrCode(0);
                userData.setErrMessage("Ok");

                // Exclude password before sending it to the client
                user.setPassword(null);
                userData.setUser(user);
            } else {
                userData.setErrCode(3);
                userData.setErrMessage("Wrong password");
            }
        } else {
            userData.setErrCode(2);
            userData.setErrMessage("User not found");
        }

        return userData;
    }

    @Override
    public boolean checkUserEmail(String userEmail) {
        return userRepository.existsByEmail(userEmail);
    }

    @Override
    public UserResponse getAllUsers(String userId) {
        UserResponse userData = new UserResponse();

        if ("ALL".equals(userId)) {
            List<User> users = userRepository.findAllByPasswordNot(null);
            users.forEach(user -> user.setPassword(null));
            userData.setUsers(users);
        } else {
            Long id = Long.parseLong(userId);
            Optional<User> optionalUser = userRepository.findById(id);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setPassword(null);
                userData.setUsers(List.of(user));
            }
        }

        return userData;
    }

    @Override
    public UserResponse createNewUser(UserRequest data) {
        UserResponse response = new UserResponse();

        if (userRepository.existsByEmail(data.getEmail())) {
            response.setErrCode(1);
            response.setErrMessage("Email is already in use");
        } else {
            User newUser = new User();
            newUser.setEmail(data.getEmail());
            newUser.setPassword(passwordEncoder.encode(data.getPassword()));
            newUser.setFirstName(data.getFirstName());
            newUser.setLastName(data.getLastName());
            newUser.setAddress(data.getAddress());
            newUser.setPhoneNumber(data.getPhoneNumber());
            newUser.setGender(data.getGender());
            newUser.setRoles(data.getRoleIds());
            newUser.setPositionId(data.getPositionId());
            newUser.setImage(data.getImage());

            userRepository.save(newUser);

            response.setErrCode(0);
            response.setErrMessage("User created successfully");
        }

        return response;
    }

    @Override
    public UserResponse deleteUser(Long userId) {
        UserResponse response = new UserResponse();

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            response.setErrCode(0);
            response.setErrMessage("User deleted successfully");
        } else {
            response.setErrCode(2);
            response.setErrMessage("The user isn't exist");
        }

        return response;
    }

    @Override
    public UserResponse updateUserData(UserRequest data) {
        UserResponse response = new UserResponse();

        Optional<User> optionalUser = userRepository.findById(data.getId());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(data.getFirstName());
            user.setLastName(data.getLastName());
            user.setAddress(data.getAddress());
            user.setRoles(data.getRoleIds());
            user.setPositionId(data.getPositionId());
            user.setGender(data.getGender());
            user.setPhoneNumber(data.getPhoneNumber());

            if (data.getImage() != null) {
                user.setImage(data.getImage());
            }

            userRepository.save(user);

            response.setErrCode(0);
            response.setErrMessage("Update the user succeeds");
        } else {
            response.setErrCode(1);
            response.setErrMessage("User not found");
        }

        return response;
    }

    @Override
    public UserResponse getAllCodeService(String typeInput) {
        return null;
    }

//    @Override
//    public UserResponse getAllCodeService(String typeInput) {
//        UserResponse response = new UserResponse();
//
//        if (typeInput != null) {
//            List<Allcodes> allcodeList = allcodeRepository.findAllByType(typeInput);
//
//            response.setErrCode(0);
//            response.setData(allcodeList);
//        } else {
//            response.setErrCode(1);
//            response.setErrMessage("Missing required parameters");
//        }
//
//        return response;
//    }

}
