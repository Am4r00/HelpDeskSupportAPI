package com.helpdesk.supportapi.Model;

import com.helpdesk.supportapi.Model.Enums.Priority;
import com.helpdesk.supportapi.Model.Enums.StatusCalled;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Called {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private StatusCalled status;

    @ManyToOne
    private Category category;


    private LocalDateTime creationDate;
    private LocalDateTime  updateDate;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "User_Responsible_id")
    private User responsibleUser;

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @PrePersist
    public void setCreationDate(){
        this.creationDate = LocalDateTime.now();
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public User getResponsibleUser() {
        return responsibleUser;
    }

    @PreUpdate
    public void setUpdateDate(){
        this.updateDate = LocalDateTime.now();
    }

    public void setResponsibleUser(User responsibleUser) {
        this.responsibleUser = responsibleUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public StatusCalled getStatus() {
        return status;
    }

    public void setStatus(StatusCalled status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
