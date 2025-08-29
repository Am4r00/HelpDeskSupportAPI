package com.helpdesk.supportapi.exception.business;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String message) {
        super(message);
    }

    public EmailAlreadyUsedException(){
        super("Email already registered");
    }
}
