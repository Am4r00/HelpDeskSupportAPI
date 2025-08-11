package com.helpdesk.supportapi.exceptions;

public class EmailNotFound extends RuntimeException{
    public EmailNotFound(String message) {
        super(message);
    }

    public EmailNotFound(){
        super("Email not found or invalid");
    }
}
