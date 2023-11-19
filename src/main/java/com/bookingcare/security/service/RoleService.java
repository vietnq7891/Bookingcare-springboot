package com.bookingcare.security.service;

import com.bookingcare.common.ApiResponse;
import com.bookingcare.security.entities.Role;
import com.bookingcare.security.entities.User;
import com.bookingcare.security.repo.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public ApiResponse<Role> save(Role role) {
        ApiResponse<Role> response = new ApiResponse<>();

        try {
            // Lưu role vào cơ sở dữ liệu
            Role savedRole = roleRepository.save(role);

            // Cập nhật thông tin phản hồi
            response.setData(savedRole);
            response.setErrMessage("Role created/updated successfully");
        } catch (Exception e) {
            // Xử lý các loại ngoại lệ nếu cần
            response.setErrCode(1); // Đặt mã lỗi tùy thuộc vào ngữ cảnh cụ thể
            response.setErrMessage("Error creating/updating role: " + e.getMessage());
        }

        return response;
    }

    @Override
    public ApiResponse remove(Integer id) {
        return null;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
