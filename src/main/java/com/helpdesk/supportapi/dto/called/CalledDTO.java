package com.helpdesk.supportapi.dto.called;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesk.supportapi.model.enums.Priority;
import com.helpdesk.supportapi.model.enums.StatusCalled;

import java.time.LocalDateTime;

/**
 * Classe que apenas mostra o nescessário do chamado.
 * Class that only shows what is necessary for the call.
 */
public class CalledDTO {
    private Long id;
    private String title;
    private Priority priority;
    private StatusCalled status;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updateDate;

    public CalledDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public StatusCalled getStatus() {
        return status;
    }

    public void setStatus(StatusCalled status) {
        this.status = status;
    }

    public LocalDateTime getUpdateDate() {return updateDate;}

    public void setUpdateDate(LocalDateTime updateDate) {this.updateDate = updateDate;}

}
