package com.helpdesk.supportapi.dto.called;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public class CalledCreateDTO {
    @NotBlank(message = "title is mandatory")
    @Size(min = 5, max = 30)
    private String title;

    @NotBlank
    @Size(min = 10, max = 1000)
    @NotBlank(message = "description is mandatory")
    private String description;

    @Positive
    @NotNull
    @NotBlank(message = "categoryId is mandatory")
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

    public Long getCategoryID() {
        return categoryId;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryId = categoryID;
    }
}
