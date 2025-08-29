package com.helpdesk.supportapi.dto.called.response;

/**
 * Classe que mostra apenas o necessário para incluir dentro do chamado.
 * Class that shows only what is necessary to include within the call.
 */
public class UserSummaryResponse {
    private Long id;
    private String name;
    private String email;

    public UserSummaryResponse() {
    }

    public UserSummaryResponse(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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
}
