package com.bookingcare.common;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private int errCode;
    private String errMessage;
    private T data;

    public ApiResponse(int errCode, String errMessage, T data) {
        this.errCode = errCode;
        this.errMessage = errMessage;
        this.data = data;
    }

    public ApiResponse() {

    }
}