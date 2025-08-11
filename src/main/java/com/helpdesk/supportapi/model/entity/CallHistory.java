package com.helpdesk.supportapi.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CallHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(length = 510, nullable = false)
    private String eventDescription;

    @ManyToOne(optional = false, fetch =  FetchType.LAZY)
    @JoinColumn(name = "called_id", nullable = false)
    private Called called;

    @ManyToOne(optional = false, fetch =  FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    public void setCreatedAt(){
        if(createdAt ==null) this.createdAt = LocalDateTime.now();}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Called getCalled() {
        return called;
    }

    public void setCalled(Called called) {
        this.called = called;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}