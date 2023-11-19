package com.bookingcare.exception;

public class BaseException extends RuntimeException {
    private final ErrorMessage errorMessage;

    public BaseException(int errorCode, String message) {
        super(String.valueOf(errorCode));
        this.errorMessage = ErrorMessage.builder()
                .code(errorCode)
                .message(message)
                .build();
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}

