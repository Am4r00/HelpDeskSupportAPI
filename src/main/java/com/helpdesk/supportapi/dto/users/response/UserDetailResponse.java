package com.helpdesk.supportapi.dto.users.response;

import com.helpdesk.supportapi.model.enums.Position;
import com.helpdesk.supportapi.model.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

public class UserDetailResponse {
    private Long id;
    private String name;
    private String email;
    private List<Position> positions;
    private Status status;
    private LocalDateTime creationDate;

    public UserDetailResponse(Long id, String name, String email, List<Position> positions, Status status, LocalDateTime creationDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.positions = positions;
        this.status = status;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
