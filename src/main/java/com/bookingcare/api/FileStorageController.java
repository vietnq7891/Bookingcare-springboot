package com.bookingcare.api;

import com.bookingcare.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/files")
public class FileStorageController {
    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        fileStorageService.save(file);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/load/{filename:.+}")
    public ResponseEntity<byte[]> getFile(@PathVariable String filename) {
        try {
            Resource resource = fileStorageService.load(filename);
            byte[] fileData = Files.readAllBytes(Path.of(resource.getURI()));

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)  // Thay đổi kiểu dữ liệu content type tùy thuộc vào kiểu file
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(fileData);
        } catch (IOException e) {
            // Xử lý lỗi khi đọc file
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}