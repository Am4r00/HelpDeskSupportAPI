package com.helpdesk.supportapi.exception.business;

public class UserWithoutPermissionException extends RuntimeException {
    public UserWithoutPermissionException(String message) {
        super(message);
    }
}
