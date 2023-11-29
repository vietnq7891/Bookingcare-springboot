package com.bookingcare.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;



public interface FileStorageService {
    String save(MultipartFile file);
    Resource load(String fileName);

    String saveBase64(String base64Data, String fileName);
}