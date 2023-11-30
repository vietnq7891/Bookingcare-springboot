package com.bookingcare.security.controller;
import com.bookingcare.common.ApiResponse;
import com.bookingcare.exception.BaseException;
import com.bookingcare.model.entity.Allcode;
import com.bookingcare.security.entities.User;
import com.bookingcare.security.jwt.JwtResponse;
import com.bookingcare.security.jwt.JwtService;
import com.bookingcare.security.service.IUserService;
import com.bookingcare.service.FileStorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @Autowired
    private FileStorageService fileStorageService;


    @PostMapping("/login")
    public ResponseEntity<?> handleLogin(@RequestBody User user) {
        String userName = user.getUsername();
        String password = user.getPassword();
        if(userName == null || userName.isEmpty() || password == null || password.isEmpty()) {
            throw new BaseException(1, "Missing inputs parameters!");
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtService.generateTokenLogin(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User currentUser = userService.findByUsername(user.getUsername()).get();
            JwtResponse jwtResponse = new JwtResponse(jwt, currentUser);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(0, "OK", jwtResponse));
        }catch (UsernameNotFoundException | BadCredentialsException e) {
            throw new BaseException(1, "Incorrect username or password.");
        } catch (Exception e) {
            throw e;
        }

    }

    @GetMapping("/get-all-users")
    public ResponseEntity<ApiResponse<List<User>>> handleGetAllUsers() {
        try {
            List<User> users = userService.findAll();


            if (!users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(0, "OK", users));
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>(204, "No users found", null));
            }
        } catch (Exception e) {
            throw e;
        }
    }




    @PostMapping("/create-new-user")
    public ResponseEntity<ApiResponse<User>> handleCreateNewUser(
            @RequestParam("user") String userData,
            @RequestParam("avatar") String avatarBase64) {
        try {
            // Chuyển đổi dữ liệu người dùng từ JSON
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(userData, User.class);


            // Kiểm tra xem avatarBase64 có dữ liệu không
            if (StringUtils.isEmpty(avatarBase64)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Avatar is required", null));
            }

            // Upload avatar từ base64 và lưu thông tin người dùng
            String avatarUrl = fileStorageService.saveBase64(avatarBase64,  user.getRoleId() );
            if (StringUtils.isEmpty(avatarUrl)) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to save avatar", null));
            }
            user.setAvatar(avatarUrl);

            // Kiểm tra dữ liệu người dùng trước khi lưu
            if (user == null || user.getUsername() == null || user.getEmail() == null || user.getPassword() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Invalid user data", null));
            }

            ApiResponse<User> response = userService.save(user);

            if (response.getErrCode() == 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            // Xử lý lỗi khi chuyển đổi dữ liệu người dùng hoặc khi gọi service
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", null));
        }
    }


    @PutMapping("/edit-user")
    public ResponseEntity<ApiResponse<User>> handleEditUser(
            @RequestParam("user") String userData,
            @RequestParam("avatar") String avatarBase64) {
        try {
            // Chuyển đổi dữ liệu người dùng từ JSON
            ObjectMapper objectMapper = new ObjectMapper();
            User updatedUser = objectMapper.readValue(userData, User.class);


//             Kiểm tra xem avatarBase64 có dữ liệu không
            if (!StringUtils.isEmpty(avatarBase64)) {


                // Upload avatar từ base64 và lưu thông tin người dùng
                String avatarUrl = fileStorageService.saveBase64(avatarBase64, updatedUser.getRoleId() );
                if (StringUtils.isEmpty(avatarUrl)) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to save avatar", null));
                }
                updatedUser.setAvatar(avatarUrl);
            }

            // Lưu thông tin người dùng cập nhật
            ApiResponse<User> response = userService.updateUserData(updatedUser);

            if (response.getErrCode() == 0) {
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            // Xử lý lỗi khi chuyển đổi dữ liệu người dùng hoặc khi gọi service
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", null));
        }
    }


    @DeleteMapping("/delete-user")
    public ResponseEntity<ApiResponse> deleteUser(@RequestBody User user) {
        Integer userId = user.getId();
        ApiResponse response = userService.remove(userId);
        if (response.getErrCode() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/allcode")
    public ResponseEntity<ApiResponse<List<Allcode>>> getAllCode(@RequestParam String type) {
        try {
            ApiResponse<List<Allcode>> data  = userService.getAllCode(type);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Get all code error: " + e.getMessage());
            return new ResponseEntity<>(
                    new ApiResponse<>(-1, "Error from server", null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


}
