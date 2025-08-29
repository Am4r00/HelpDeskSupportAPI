package com.helpdesk.supportapi.dto.user.response;

import com.helpdesk.supportapi.model.enums.Position;

import java.util.List;

/**
 * Classe para expor apenas o nescessário do Usuário.
 * Class to expose only what the User needs.
 */
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private List<Position> positions;

    public UserResponse(Long id, String name, String email, List<Position> positions) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.positions = positions;
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
}
