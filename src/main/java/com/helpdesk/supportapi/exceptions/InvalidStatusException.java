package com.helpdesk.supportapi.exceptions;

public class InvalidStatusException extends RuntimeException{
    public InvalidStatusException(String message) {
        super(message);
    }
}
