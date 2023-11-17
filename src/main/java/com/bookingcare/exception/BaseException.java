package com.bookingcare.exception;

public class BaseException extends RuntimeException{
    ErrorMessage errorMessage;
    public BaseException(int errorCode, String message ) {
        super(String.valueOf(errorCode));
        errorMessage = new ErrorMessage();
        errorMessage.setCode(errorCode);
        errorMessage.setMessage(message);
    }
}
