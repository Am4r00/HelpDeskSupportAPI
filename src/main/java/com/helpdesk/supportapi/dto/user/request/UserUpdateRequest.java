package com.helpdesk.supportapi.dto.user.request;

import com.helpdesk.supportapi.model.enums.Position;
import com.helpdesk.supportapi.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;

public class UserUpdateRequest {
    @NotBlank
    @Size(min = 2, max = 60)
    public String name;

    @NotNull
    @Size(min = 1, message = "Pelo menos uma posição deve ser atribuída")
    public Set<Position> positions;

    @NotNull
    public Status status;

    public UserUpdateRequest(String name, List<Position> positions, Status status) {
    }

    public UserUpdateRequest(String name, Set<Position> positions, Status status) {
        this.name = name;
        this.positions = positions;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
