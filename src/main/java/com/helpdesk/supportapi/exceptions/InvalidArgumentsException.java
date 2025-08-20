package com.helpdesk.supportapi.exceptions;

public class InvalidArgumentsException extends RuntimeException {
    public InvalidArgumentsException(String message) {
        super(message);
    }

    public InvalidArgumentsException(){
        super("Invalid argument");
    }
}
