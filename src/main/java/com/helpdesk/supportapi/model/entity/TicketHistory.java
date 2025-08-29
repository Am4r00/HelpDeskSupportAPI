package com.helpdesk.supportapi.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TicketHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(length = 510, nullable = false)
    private String eventDescription;

    @ManyToOne(optional = false, fetch =  FetchType.LAZY)
    @JoinColumn(name = "called_id", nullable = false)
    private Ticket ticket;

    @ManyToOne(optional = false, fetch =  FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User userResponsible;

    public TicketHistory() {
    }

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

    public Ticket getCalled() {
        return ticket;
    }

    public void setCalled(Ticket ticket) {
        this.ticket = ticket;
    }

    public User getUser() {
        return userResponsible;
    }

    public void setUser(User userResponsible) {
        this.userResponsible = userResponsible;
    }
}