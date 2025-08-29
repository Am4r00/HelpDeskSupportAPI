package com.helpdesk.supportapi.dto.called.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class AssignTicketRequest {
    @Positive
    @NotNull(message="UserResponsibleId is mandatory")
    private Long UserResponsibleId;

    @Size(max = 500)
    private String note;

    public long getResponsibleId() {
        return UserResponsibleId;
    }

    public void setResponsibleId(long responsibleId) {
        this.UserResponsibleId = responsibleId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
