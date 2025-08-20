package com.helpdesk.supportapi.exceptions;

public class UserWithoutPermissionException extends RuntimeException {
    public UserWithoutPermissionException(String message) {
        super(message);
    }
}
