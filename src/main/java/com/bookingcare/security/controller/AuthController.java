package com.bookingcare.security.controller;
import com.bookingcare.common.ApiResponse;
import com.bookingcare.exception.BaseException;
import com.bookingcare.security.entities.User;
import com.bookingcare.security.jwt.JwtResponse;
import com.bookingcare.security.jwt.JwtService;
import com.bookingcare.security.service.IUserService;
import com.bookingcare.service.FileStorageService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
            JwtResponse jwtResponse = new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), currentUser.getEmail(), userDetails.getAuthorities());
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
    public ResponseEntity<ApiResponse<User>> handleCreateNewUser(@RequestBody User user, @RequestParam("avatar") MultipartFile avatarFile) {
        try {
            if (!avatarFile.isEmpty()) {
                // Lưu file avatar
                fileStorageService.save(avatarFile);

                // Cập nhật tên file avatar trong đối tượng User
                user.setAvatar(avatarFile.getOriginalFilename());
            }

            ApiResponse<User> response = userService.save(user);

            if (response.getErrCode() == 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            // Xử lý lỗi khi lưu file hoặc khi gọi service
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("edit-user")
    public ResponseEntity<ApiResponse<User>> handleEditUser(@RequestBody User userData) {
        ApiResponse<User> response = userService.updateUserData(userData);

        if (response.getErrCode() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<ApiResponse> deleteUser(@RequestParam int id) {
//        Integer userId = request.get("id");
        ApiResponse response = userService.remove(id);
        if (response.getErrCode() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/allcode")
    public ResponseEntity<Object> getAllCode(@RequestBody String type) {
        try {
            Object data = userService.getAllCode(type);
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
