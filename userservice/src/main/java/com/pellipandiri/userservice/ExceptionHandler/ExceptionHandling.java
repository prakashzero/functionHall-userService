package com.pellipandiri.userservice.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

class ErrorResponse{
    String message;
    LocalDateTime localDate;
    int  httpStatus;

    ErrorResponse(String message,LocalDateTime localDate,int httpStatus){
        this.message=message;
        this.localDate = localDate;
        this.httpStatus= httpStatus;
    }
}

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(UserRequestException.class)
    public ResponseEntity<ErrorResponse> userRequestException(UserRequestException userRequestException){
        ErrorResponse errorResponse = new ErrorResponse(userRequestException.getMessage(),
                LocalDateTime.now(),HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FunctionNameNotFound.class)
    public ResponseEntity<ErrorResponse> functionNameNotFound(FunctionNameNotFound functionNameNotFound){
        ErrorResponse errorResponse = new ErrorResponse(functionNameNotFound.getMessage(),
                LocalDateTime.now(),HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BookingException.class)
    public ResponseEntity<ErrorResponse> bookingStatusConflictException(BookingException bookingException){
        ErrorResponse errorResponse = new ErrorResponse(bookingException.getMessage(),
                LocalDateTime.now(),HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OwnerException.class)
    public ResponseEntity<ErrorResponse> ownerException(OwnerException ownerException){
        ErrorResponse errorResponse = new ErrorResponse(ownerException.getMessage(),
                LocalDateTime.now(),HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
