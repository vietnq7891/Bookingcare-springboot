package com.bookingcare.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestControllerAdvice
@Slf4j
public class CommonHandleException {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Object> handleBaseException(BaseException ex, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(ex.getErrorMessage(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleThrowable(Throwable ex, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        // Xử lý các loại ngoại lệ khác nếu cần
        return new ResponseEntity<>(ErrorMessage.builder()
                .code(500)
                .message("Internal Server Error")
                .build(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
