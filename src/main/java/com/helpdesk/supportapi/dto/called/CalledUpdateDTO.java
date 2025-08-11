package com.helpdesk.supportapi.dto.called;

import com.helpdesk.supportapi.model.enums.Priority;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CalledUpdateDTO {

    @Size(min = 5, max = 30)
    private String title;

    @Size(min = 10, max = 2000)
    private String description;

    private Priority priority;

    @Positive
    private Long categoryId;


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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
