package com.helpdesk.supportapi.model.enums;

public enum StatusCalled {
    OPEN("Open Call"),
    IN_PROGRESS("Call in Progress"),
    RESOLVED("Call Resolved"),
    CLOSE("Closed Call");

    private final String label;

    StatusCalled(String label) {
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}
