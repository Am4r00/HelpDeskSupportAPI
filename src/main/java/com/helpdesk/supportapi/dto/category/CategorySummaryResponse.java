package com.helpdesk.supportapi.dto.category;

/**
 * Classe que mostra apenas o necessário para incluir dentro do chamado.
 * Class that shows only what is necessary to include within the call.
 */
public class CategorySummaryResponse {
    private Long id;
    private String name;

    public CategorySummaryResponse() {
    }

    public CategorySummaryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
