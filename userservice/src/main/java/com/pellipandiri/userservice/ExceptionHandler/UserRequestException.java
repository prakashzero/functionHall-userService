package com.pellipandiri.userservice.ExceptionHandler;

public class UserRequestException extends Exception{

    public UserRequestException(String message){
        super(message);
    }
}
