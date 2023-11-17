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
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleException(Throwable ex, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(ErrorMessage.builder()
                .code(0)
                .message("Lỗi gì ai biết")
                .build(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
//    @ExceptionHandler(BadCredentialsException.class)
//    public ErrorMessage LoginException(Exception ex, WebRequest request) {
//        return new ErrorMessage(1, "Missing inputs parameters!");
//    }
}
