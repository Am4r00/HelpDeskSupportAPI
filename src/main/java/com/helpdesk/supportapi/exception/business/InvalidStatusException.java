package com.helpdesk.supportapi.exception.business;

public class InvalidStatusException extends RuntimeException{
    public InvalidStatusException(String message) {
        super(message);
    }
}
