package com.helpdesk.supportapi.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CallHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateHour;
    private String eventDescription;

    @ManyToOne
    private Called called;

    @ManyToOne
    private User user;

    public LocalDateTime getDateHour() {
        return dateHour;
    }

    @PrePersist
    public void setDateHour(){
        this.dateHour = LocalDateTime.now();
    }

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
