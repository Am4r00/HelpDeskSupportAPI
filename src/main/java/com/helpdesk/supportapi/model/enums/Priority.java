package com.helpdesk.supportapi.model.enums;

public enum Priority {
    LOW("P4"),
    AVERAGE("P3"),
    HIGH("P2"),
    CRITICAL("P1");

    private final String label;

    Priority(String label) {
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}

