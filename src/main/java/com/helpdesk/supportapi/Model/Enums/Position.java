package com.helpdesk.supportapi.Model.Enums;


public enum Position {
    ADMIN("Admin"),
    SUPPORT("Support"),
    CUSTOMER("Customers");

    private final String label;

    Position(String label) {
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}
