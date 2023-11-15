package com.bookingcare.api;
import com.bookingcare.model.dto.*;
import com.bookingcare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> handleLogin(@RequestBody LoginRequest request) {
        try {
            String email = request.getEmail();
            String password = request.getPassword();

            // Kiểm tra xem đã nhập vào chưa
            if (email == null || password == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse(1, "Missing inputs parameters!"));
            }

            UserResponse userData = userService.handleUserLogin(email, password);

            return ResponseEntity.ok(new ApiResponse(userData.getErrCode(), userData.getErrMessage(), userData.getUser()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(-1, "Error from server"));
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> handleGetAllUsers(@RequestParam(value = "id") String id) {
        try {
            // Kiểm tra id
            if (id == null) {
                return ResponseEntity.ok(new ApiResponse(1, "Missing required parameters", null));
            }

            UserResponse users = userService.getAllUsers(id);

            return ResponseEntity.ok(new ApiResponse(0, "OK", users.getUsers()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(-1, "Error from server"));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> handleCreateNewUser(@RequestBody UserRequest data) {
        try {
            UserResponse response = userService.createNewUser(data);

            return ResponseEntity.ok(new ApiResponse(response.getErrCode(), response.getErrMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(-1, "Error from server"));
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<Object> handleEditUser(@RequestBody UserRequest data) {
        try {
            UserResponse response = userService.updateUserData(data);

            return ResponseEntity.ok(new ApiResponse(response.getErrCode(), response.getErrMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(-1, "Error from server"));
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> handleDeleteUser(@RequestBody DeleteUserRequest request) {
        try {
            // Kiểm tra id
            if (request.getId() == null) {
                return ResponseEntity.ok(new ApiResponse(1, "Missing required parameters"));
            }

            UserResponse response = userService.deleteUser(request.getId());

            return ResponseEntity.ok(new ApiResponse(response.getErrCode(), response.getErrMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(-1, "Error from server"));
        }
    }

    @GetMapping("/getAllCode")
    public ResponseEntity<Object> getAllCode(@RequestParam(value = "type") String type) {
        try {
            UserResponse data = userService.getAllCodeService(type);

            return ResponseEntity.ok(new ApiResponse(data.getErrCode(), data.getErrMessage(), data.getData()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(-1, "Error from server"));
        }
    }
}
