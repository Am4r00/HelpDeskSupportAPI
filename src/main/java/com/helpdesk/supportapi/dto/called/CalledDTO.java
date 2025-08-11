package com.helpdesk.supportapi.dto.called;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesk.supportapi.model.enums.Priority;
import com.helpdesk.supportapi.model.enums.StatusCalled;

import java.time.LocalDateTime;

public class CalledDTO {
    private Long id;
    private String title;
    private Priority priority;
    private StatusCalled status;
    private CategoryMinDTO category;

    private UserMinDTO responsible;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime creationDate;

    public CalledDTO() {
    }

    public CalledDTO(Long id, String title, Priority priority, StatusCalled status, CategoryMinDTO category, LocalDateTime creationDate, UserMinDTO responsible) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.status = status;
        this.category = category;
        this.creationDate = creationDate;
        this.responsible =responsible;
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

    public CategoryMinDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryMinDTO category) {
        this.category = category;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public UserMinDTO getResponsible() {return responsible;}

    public void setResponsible(UserMinDTO responsible) {this.responsible = responsible;}
}
