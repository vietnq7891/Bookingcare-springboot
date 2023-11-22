package com.bookingcare.service.Impl;

import com.bookingcare.exception.BaseException;
import com.bookingcare.service.FileStorageService;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final String uploadDir = "./uploads"; // Đường dẫn tới thư mục lưu trữ file

    @Override
    public void save(MultipartFile file) {
        try {
            // Tạo thư mục nếu nó chưa tồn tại
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Lưu file vào thư mục
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);

        } catch (IOException e) {
            throw new BaseException(3,"Failed to store file: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String fileName) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new BaseException(3, "File not found:" + fileName);
            }
        } catch (MalformedURLException e) {
            throw new BaseException(3, "File not found:" + fileName);
        }
    }


}