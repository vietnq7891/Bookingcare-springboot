package com.bookingcare.service.Impl;

import com.bookingcare.exception.BaseException;
import com.bookingcare.service.FileStorageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.Base64;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileStorageServiceImpl.class);
    private final Path root = Paths.get("uploads");

    public FileStorageServiceImpl() {
        try {
            Files.createDirectories(root);
        } catch (Exception ex) {
            LOGGER.error("FileStorageServiceImpl createDirectories with ex: {}", ex);
            throw new BaseException(500, "INTERNAL_SERVER_ERROR");
        }
    }

    @Override
    public String save(MultipartFile file) {
        try {
            Path filePath = root.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Trả về đường dẫn tương đối của tệp tin đã lưu
            String relativePath = root.relativize(filePath).toString();
            return relativePath;
        } catch (FileAlreadyExistsException ex) {
            LOGGER.warn(ex.getMessage());
            // Xử lý trường hợp tệp tin đã tồn tại nếu cần
            throw new BaseException(2, "FILE_ALREADY_EXISTS");
        } catch (Exception ex) {
            LOGGER.error("FileStorageServiceImpl save with ex: {}", ex);
            throw new BaseException(1, "UPLOAD_ERROR");
        }
    }

    @Override
    public Resource load(String fileName) {
        try {
            Path file = root.resolve(fileName);

            // Trả về đường dẫn tương đối của tệp tin đã lưu
            String relativePath = root.relativize(file).toString();

            Resource resource = new UrlResource(root.toUri().resolve(relativePath));
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new BaseException(2, "LOAD_FILE_ERROR");
            }
        } catch (Exception ex) {
            LOGGER.error("FileStorageServiceImpl load with ex: {}", ex);
            throw new BaseException(1, "UPLOAD_ERROR");
        }
    }




    @Override
    public String saveBase64(String base64Data, String fileName) {
        try {
            byte[] fileBytes = Base64.getDecoder().decode(base64Data);

            Path filePath = root.resolve(fileName);
            Files.write(filePath, fileBytes);

            // Trả về đường dẫn tương đối của tệp tin đã lưu
            String relativePath = root.relativize(filePath).toString();
            return relativePath;
        } catch (Exception ex) {
            LOGGER.error("FileStorageServiceImpl saveBase64 with ex: {}", ex);
            throw new BaseException(1, "UPLOAD_ERROR");
        }
    }

}