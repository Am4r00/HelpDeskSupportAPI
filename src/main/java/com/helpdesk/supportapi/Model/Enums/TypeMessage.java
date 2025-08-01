package com.helpdesk.supportapi.Model.Enums;

public enum TypeMessage {
    CUSTOMER("Customer"),
    SYSTEM("System"),
    SUPPORT("Support");

    private final String label;

    TypeMessage(String label) {
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}
