package com.helpdesk.supportapi.dto.called;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesk.supportapi.dto.category.CategoryMinDTO;
import com.helpdesk.supportapi.model.enums.Priority;
import com.helpdesk.supportapi.model.enums.StatusCalled;

import java.time.LocalDateTime;

public class CalledDetailDTO {
    private Long id;
    private String title;
    private String description;

    private Priority priority;

    private StatusCalled status;

    private UserMinDTO requester;

    private CategoryMinDTO category;

    private UserMinDTO responsible;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime creationDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime  updateDate;

    public CalledDetailDTO() {
    }

    public CalledDetailDTO(Long id, String title, String description, Priority priority, StatusCalled status, UserMinDTO requester, CategoryMinDTO category, UserMinDTO responsible, LocalDateTime creationDate, LocalDateTime updateDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.requester = requester;
        this.category = category;
        this.responsible = responsible;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public UserMinDTO getRequester() {
        return requester;
    }

    public void setRequester(UserMinDTO requester) {
        this.requester = requester;
    }

    public CategoryMinDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryMinDTO category) {
        this.category = category;
    }

    public UserMinDTO getResponsible() {
        return responsible;
    }

    public void setResponsible(UserMinDTO responsible) {
        this.responsible = responsible;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
