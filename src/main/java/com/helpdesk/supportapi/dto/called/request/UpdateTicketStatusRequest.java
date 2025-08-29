package com.helpdesk.supportapi.dto.called.request;

import com.helpdesk.supportapi.model.enums.StatusTicket;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateTicketStatusRequest {

    @NotNull(message = "newStatus is mandatory")
    private StatusTicket newStatus;

    @Size(max = 500)
    private String reason;


    public StatusTicket getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(StatusTicket newStatus) {
        this.newStatus = newStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
