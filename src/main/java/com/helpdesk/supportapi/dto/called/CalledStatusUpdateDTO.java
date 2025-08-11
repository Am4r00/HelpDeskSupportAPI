package com.helpdesk.supportapi.dto.called;

import com.helpdesk.supportapi.model.enums.StatusCalled;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CalledStatusUpdateDTO {

    @NotNull(message = "newStatus is mandatory")
    private StatusCalled newStatus;

    @Size(max = 500)
    private String reason;


    public StatusCalled getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(StatusCalled newStatus) {
        this.newStatus = newStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
