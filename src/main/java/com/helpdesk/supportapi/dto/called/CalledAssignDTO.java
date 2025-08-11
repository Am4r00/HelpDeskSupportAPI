package com.helpdesk.supportapi.dto.called;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CalledAssignDTO {
    @Positive
    @NotNull(message="responsibleUserId is mandatory")
    private Long responsibleUserId ;

    @Size(max = 500)
    private String note;

    public long getResponsibleId() {
        return responsibleUserId ;
    }

    public void setResponsibleId(long responsibleId) {
        this.responsibleUserId  = responsibleId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
