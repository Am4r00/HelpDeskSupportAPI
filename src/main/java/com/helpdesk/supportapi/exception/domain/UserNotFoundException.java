package com.helpdesk.supportapi.exception.domain;

public class UserNotFoundException  extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(){
        super("User not found or invalid!");
    }
}
