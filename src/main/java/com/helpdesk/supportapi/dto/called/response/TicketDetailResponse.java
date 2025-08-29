package com.helpdesk.supportapi.dto.called.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesk.supportapi.dto.category.CategorySummaryResponse;
import com.helpdesk.supportapi.model.enums.Priority;
import com.helpdesk.supportapi.model.enums.StatusTicket;

import java.time.LocalDateTime;

public class TicketDetailResponse {
    private Long id;
    private String title;
    private String description;

    private Priority priority;

    private StatusTicket status;

    private UserSummaryResponse requester;

    private CategorySummaryResponse category;

    private UserSummaryResponse responsible;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime creationDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime  updateDate;

    public TicketDetailResponse() {
    }

    public TicketDetailResponse(Long id, String title, String description, Priority priority, StatusTicket status, UserSummaryResponse requester, CategorySummaryResponse category, UserSummaryResponse responsible, LocalDateTime creationDate, LocalDateTime updateDate) {
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

    public StatusTicket getStatus() {
        return status;
    }

    public void setStatus(StatusTicket status) {
        this.status = status;
    }

    public UserSummaryResponse getRequester() {
        return requester;
    }

    public void setRequester(UserSummaryResponse requester) {
        this.requester = requester;
    }

    public CategorySummaryResponse getCategory() {
        return category;
    }

    public void setCategory(CategorySummaryResponse category) {
        this.category = category;
    }

    public UserSummaryResponse getResponsible() {
        return responsible;
    }

    public void setResponsible(UserSummaryResponse responsible) {
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
