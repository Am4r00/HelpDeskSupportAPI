package com.helpdesk.supportapi.model.enums;

public enum Status {
    ACTIVE("User Active"),
    INACTIVE("User Inactive");

    private final String label;

    Status(String label) {
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}
