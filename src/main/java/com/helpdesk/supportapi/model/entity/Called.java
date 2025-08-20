package com.helpdesk.supportapi.model.entity;

import com.helpdesk.supportapi.model.enums.Priority;
import com.helpdesk.supportapi.model.enums.StatusCalled;
import jakarta.persistence.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Called {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 25)
    private String title;

    @Column(nullable = false, length = 510)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCalled status;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Category category;

    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;

    private LocalDateTime  updateDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    private User userResponsible;

    @OneToMany
    private List<CallHistory> callHistory;

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
        return userResponsible;
    }

    @PreUpdate
    public void setUpdateDate(){
        this.updateDate = LocalDateTime.now();
    }

    public void setResponsibleUser(User userResponsible) {
        this.userResponsible = userResponsible;
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

    public List<CallHistory> getCallHistory() {
        return callHistory;
    }

    public void setCallHistory(List<CallHistory> callHistory) {
        this.callHistory = callHistory;
    }
}
