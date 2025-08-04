package com.pellipandiri.userservice.ExceptionHandler;

public class OwnerException extends RuntimeException {
    
    public OwnerException(String message) {
        super(message);
    }
    
    public OwnerException(String message, Throwable cause) {
        super(message, cause);
    }
} 