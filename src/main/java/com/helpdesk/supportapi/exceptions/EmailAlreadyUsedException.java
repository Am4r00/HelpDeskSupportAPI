package com.helpdesk.supportapi.exceptions;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String message) {
        super(message);
    }

    public EmailAlreadyUsedException(){
        super("Email already registered");
    }
}
