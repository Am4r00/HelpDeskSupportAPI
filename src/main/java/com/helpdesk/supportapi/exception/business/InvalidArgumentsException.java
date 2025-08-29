package com.helpdesk.supportapi.exception.business;

public class InvalidArgumentsException extends RuntimeException {
    public InvalidArgumentsException(String message) {
        super(message);
    }

    public InvalidArgumentsException(){
        super("Invalid argument");
    }
}
