package com.helpdesk.supportapi.Model.Enums;

public enum Priority {
    LOW("Low Priority"),
    AVARANGE("Medium priority"),
    HIGH("High priority"),
    CRITICAL("Critical priority");

    private final String label;

    Priority(String label) {
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}

