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
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileStorageServiceImpl.class);
    private final Path root = Paths.get("uploads");

    public FileStorageServiceImpl() {
        try {
            Files.createDirectories(root);
        } catch (Exception ex) {
            LOGGER.error("FileStorageServiceImpl createDirectories with ex: {}", ex);
            throw new BaseException(500,"INTERNAL_SERVER_ERROR");
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
        } catch (FileAlreadyExistsException ex) {
            LOGGER.warn(ex.getMessage());
        }
        catch (Exception ex) {
            LOGGER.error("FileStorageServiceImpl save with ex: {}", ex);
            throw new BaseException(1, "UPLOAD_ERROR");
        }
    }

    @Override
    public Resource load(String fileName) {
        try {
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable())
                return resource;
            else
                throw new BaseException(2,"LOAD_FILE_ERROR");
        } catch (Exception ex) {
            LOGGER.error("FileStorageServiceImpl save with ex: {}", ex);
            throw new BaseException(1, "UPLOAD_ERROR");
        }
    }

}