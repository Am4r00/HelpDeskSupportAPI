package com.helpdesk.supportapi.Model;

import com.helpdesk.supportapi.Model.Enums.TypeMessage;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class CalledInterection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String message;

    private LocalDateTime dateHour;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "called_id")
    private Called called;

    @Enumerated(EnumType.STRING)
    private TypeMessage typeMessage;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Called getCalled() {
        return called;
    }

    public void setCalled(Called called) {
        this.called = called;
    }

    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }
}
