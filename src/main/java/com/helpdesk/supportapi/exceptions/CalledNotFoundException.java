package com.helpdesk.supportapi.exceptions;

public class CalledNotFoundException extends RuntimeException {
    public CalledNotFoundException(String message) {
        super(message);
    }
}
