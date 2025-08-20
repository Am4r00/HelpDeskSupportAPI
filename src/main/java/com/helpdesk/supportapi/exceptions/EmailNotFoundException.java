package com.helpdesk.supportapi.exceptions;

public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException(String message) {
        super(message);
    }

    public EmailNotFoundException(){
        super("Email not found or invalid");
    }
}
