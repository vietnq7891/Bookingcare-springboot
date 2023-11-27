package com.bookingcare.security.service;

import com.bookingcare.common.ApiResponse;
import com.bookingcare.exception.BaseException;
import com.bookingcare.model.entity.Allcode;
import com.bookingcare.repository.AllcodeRepository;
import com.bookingcare.security.UserPrinciple;
import com.bookingcare.security.entities.User;
import com.bookingcare.security.repo.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AllcodeRepository allcodeRepository;
    private void hidePasswords(List<User> users) {
        for (User user : users) {
            user.setPassword(null);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        hidePasswords(users);
        return users;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public ApiResponse<User> save(User user) {
        ApiResponse<User> response = new ApiResponse<>();

        try {
            if (userRepository.existsByUsername(user.getUsername())) {
                response.setErrCode(1); // Mã lỗi tùy thuộc vào ngữ cảnh
                response.setErrMessage("Username already exists");
                return response;
            }

            // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Date currentDate = new Date();
            user.setCreatedAt(currentDate);
            user.setUpdatedAt(currentDate);

            // Lưu người dùng vào cơ sở dữ liệu
            User savedUser = userRepository.save(user);

            // Cập nhật thông tin phản hồi
            response.setData(savedUser);
            response.setErrMessage("User created successfully");
        } catch (Exception e) {
            // Xử lý các loại ngoại lệ nếu cần
            response.setErrCode(1); // Đặt mã lỗi tùy thuộc vào ngữ cảnh cụ thể
            response.setErrMessage("Error creating user: " + e.getMessage());
        }

        return response;
    }

    @Override
    public ApiResponse remove(Integer id) {
        ApiResponse response = new ApiResponse();
        logger.info("id", id);

        try {
            Optional<User> optionalUser = userRepository.findById(id);

            if (optionalUser.isPresent()) {
                // Người dùng tồn tại, xóa người dùng
                userRepository.deleteById(id);

                // Cập nhật thông tin phản hồi
                response.setErrCode(0);
                response.setErrMessage("The user is deleted!");
            } else {
                response.setErrCode(2);
                response.setErrMessage("The user isn't exist!");
            }
        } catch (Exception e) {
            throw new BaseException(100, "Error deleting user");
        }

        return response;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(userOptional.get());
    }

    @Override
    public Optional<User> findByUsername(String username) {

        Optional<User> optionalUser = userRepository.findByUsername(username);

        // Kiểm tra xem có đối tượng User trong Optional hay không
        if (optionalUser.isPresent()) {
            // Lấy đối tượng User từ Optional
            User user = optionalUser.get();

            // Set giá trị trường 'password' thành null hoặc một giá trị mặc định nếu bạn muốn ẩn nó
            user.setPassword(null);

            // Trả lại Optional với đối tượng User đã được chỉnh sửa
            return Optional.of(user);
        }

        return optionalUser;
    }

    // Ẩn mật khẩu trong danh sách người dùng


    @Override
    public ApiResponse<User> updateUserData(User data) {
        ApiResponse<User> response = new ApiResponse<>();

        try {
            // Kiểm tra xem có thiếu thông tin bắt buộc không
            if (data.getId() == null || data.getRoleId() == null || data.getPositionId() == null || data.getGender() == null) {
                response.setErrCode(2);
                response.setErrMessage("Missing required parameters!");
                return response;
            }

            // Kiểm tra xem người dùng có tồn tại không
            User existingUser = userRepository.findById(data.getId()).orElse(null);

            if (existingUser != null) {
                // Cập nhật thông tin người dùng
                existingUser.setFirstName(data.getFirstName());
                existingUser.setLastName(data.getLastName());
                existingUser.setAddress(data.getAddress());
                existingUser.setRoleId(data.getRoleId());
                existingUser.setPositionId(data.getPositionId());
                existingUser.setGender(data.getGender());
                existingUser.setPhoneNumber(data.getPhoneNumber());
                if (data.getAvatar() != null) {
                    existingUser.setAvatar(data.getAvatar());
                }

                // Lưu người dùng đã cập nhật vào cơ sở dữ liệu
                User updatedUser = userRepository.save(existingUser);

                // Cập nhật thông tin phản hồi
                response.setData(updatedUser);
                response.setErrMessage("Update the user succeeds");
            } else {
                response.setErrCode(1);
                response.setErrMessage("User not found!");
            }
        } catch (Exception e) {
            throw new BaseException(500, "Error updating user data");
        }

        return response;
    }

    @Override
    public ApiResponse<List<Allcode>> getAllCode(String typeInput) {
        try {
            if (typeInput == null || typeInput.isEmpty()) {
                return new ApiResponse<>(1, "Missing required parameters!", null);

            } else {
                List<Allcode> allCodes = allcodeRepository.findByType(typeInput);

                if (allCodes.isEmpty()) {
                    return new ApiResponse<>(1, "No matching records found", null);
                }
                logger.info("Retrieved {} records for typeInput: {}", allCodes.size(), typeInput);
                return new ApiResponse<>(0, "Success", allCodes);
            }
        } catch (Exception e) {
            logger.error("Error retrieving all codes", e);
            return new ApiResponse<>(1, "Error retrieving all codes", null);
        }
    }

}




