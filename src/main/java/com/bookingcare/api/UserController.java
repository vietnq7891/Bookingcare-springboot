package com.bookingcare.api;

import com.bookingcare.model.entity.User;
import com.bookingcare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/login")
//    public ResponseEntity<Object> handleLogin(@RequestBody User user) {
//        try {
//            String email = user.getEmail();
//            String password = user.getPassword();
//
//            // Check if email and password are provided
//            if (email == null || password == null) {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                        .body(new ApiResponse(1, "Thiếu thông số đầu vào!"));
//            }
//
//            UserData userData = userService.handleUserLogin(email, password);
//
//            return ResponseEntity.ok(new ApiResponse(userData.getErrCode(), userData.getErrMessage(), userData.getUser()));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ApiResponse(-1, "Lỗi từ máy chủ"));
//        }
//    }
//
//    @GetMapping("/getAllUsers")
//    public ResponseEntity<Object> handleGetAllUsers(@RequestParam(required = false) String id) {
//        try {
//            // Check if id is provided
//            if (id == null) {
//                return ResponseEntity.ok(new ApiResponse(1, "Thiếu tham số bắt buộc", null));
//            }
//
//            Object users = userService.getAllUsers(id);
//
//            return ResponseEntity.ok(new ApiResponse(0, "OK", users));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ApiResponse(-1, "Lỗi từ máy chủ"));
//        }
//    }
//
//    @PostMapping("/createNewUser")
//    public ResponseEntity<Object> handleCreateNewUser(@RequestBody User user) {
//        try {
//            String message = userService.createNewUser(user);
//            return ResponseEntity.ok(new ApiResponse(0, "OK", message));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ApiResponse(-1, "Lỗi từ máy chủ"));
//        }
//    }
//
//    @PostMapping("/editUser")
//    public ResponseEntity<Object> handleEditUser(@RequestBody User user) {
//        try {
//            String message = userService.updateUserData(user);
//            return ResponseEntity.ok(new ApiResponse(0, "OK", message));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ApiResponse(-1, "Lỗi từ máy chủ"));
//        }
//    }
//
//    @PostMapping("/deleteUser")
//    public ResponseEntity<Object> handleDeleteUser(@RequestParam String id) {
//        try {
//            // Check if id is provided
//            if (id == null) {
//                return ResponseEntity.ok(new ApiResponse(1, "Thiếu tham số bắt buộc", null));
//            }
//
//            String message = userService.deleteUser(id);
//
//            return ResponseEntity.ok(new ApiResponse(0, "OK", message));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ApiResponse(-1, "Lỗi từ máy chủ"));
//        }
//    }
//
//    @GetMapping("/getAllCode")
//    public ResponseEntity<Object> getAllCode(@RequestParam String type) {
//        try {
//            Object data = userService.getAllCodeService(type);
//            return ResponseEntity.ok(data);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ApiResponse(-1, "Lỗi từ máy chủ"));
//        }
//    }
}
