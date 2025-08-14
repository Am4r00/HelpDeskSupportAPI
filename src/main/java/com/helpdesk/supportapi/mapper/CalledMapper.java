package com.helpdesk.supportapi.mapper;

import com.helpdesk.supportapi.dto.called.*;
import com.helpdesk.supportapi.dto.category.CategoryMinDTO;
import com.helpdesk.supportapi.model.entity.Called;
import com.helpdesk.supportapi.model.entity.Category;
import com.helpdesk.supportapi.model.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class CalledMapper {

    /**
     * Criar um DTO apenas para expor o necessário do chamado
     * Create a DTO just to expose what is necessary for the call
     * @param called
     * @return CalledDTO resumido
     */
    public static CalledDTO toCalledDTO(Called called) {
        if (called == null) {
            return null;
        } else {
            CalledDTO calledDTO = new CalledDTO();

            //Criando sem argumentos para evitar telescoping

            calledDTO.setId(called.getId());
            calledDTO.setTitle(called.getTitle());
            calledDTO.setPriority(called.getPriority());
            calledDTO.setStatus(called.getStatus());
            calledDTO.setUpdateDate(called.getUpdateDate());

            return calledDTO;
        }
    }

    /**
     * Criando um DTO para o called, expondo todos os dados
     * Creating a DTO for the called, exposing all data
     * @param called
     * @return o called
     */
    public static CalledDetailDTO toCalledDetailDTO(Called called) {

        if (called == null) {
            return null;
        } else {
            CalledDetailDTO calledDetailDTO = new CalledDetailDTO();

            //Criando sem argumentos para evitar telescoping

            calledDetailDTO.setId(called.getId());
            calledDetailDTO.setTitle(called.getTitle());
            calledDetailDTO.setDescription(called.getDescription());
            calledDetailDTO.setPriority(called.getPriority());
            calledDetailDTO.setStatus(called.getStatus());
            calledDetailDTO.setRequester(toUserMinDTO(called.getUser()));
            calledDetailDTO.setCategory(toCategoryMinDTO(called.getCategory()));
            calledDetailDTO.setResponsible(toUserMinDTO(called.getResponsibleUser()));
            calledDetailDTO.setCreationDate(called.getCreationDate());
            calledDetailDTO.setUpdateDate(called.getUpdateDate());

            return calledDetailDTO;
        }
    }

    /**
     * Criando um DTO para expor apenas o necessário do user no Called.
     * Creating a DTO to expose only what the user needs in Called.
     * @param user
     * @return usuário sem dados desnecessários
     */
    public static UserMinDTO toUserMinDTO(User user) {
        if (user == null) {
            return null;
        } else {
            return new UserMinDTO(
                    user.getId(),
                    user.getName(),
                    user.getEmail());
        }
    }

    /**
     * Criando um called que passa  por parâmetro tudo que o usuário pode criar
     * Creating a call that passes everything the user can create as a parameter
     * @param dto
     * @param category
     * @param userRequester
     * @return called criado
     */
    public static Called fromCreateDTO(CalledCreateDTO dto, Category category, User userRequester) {
        if (dto == null || category == null || userRequester == null) {
            return null;
        } else {
            Called called = new Called();
            called.setTitle(dto.getTitle());
            called.setDescription(dto.getDescription());
            called.setCategory(category);
            called.setUser(userRequester);

            return called;

        }
    }

    /**
     * retorna apenas o nescessário da categorio
     * returns only what is necessary for the category
     * @param category
     * @return category resumida
     */
    public static CategoryMinDTO toCategoryMinDTO(Category category) {
        if (category == null) {
            return null;
        } else {
            return new CategoryMinDTO(
                    category.getId(),
                    category.getName());
        }
    }

    /**
     * Faz uma lista de todos os chamados
     * Make a list of all calls
     * @param calledList
     * @return retorna uma lista dos calleds
     */
    public static List<CalledDTO> toCalledDTOList(List<Called> calledList) {
        return calledList.stream()
                .map(CalledMapper::toCalledDTO)
                .collect(Collectors.toList());

    }

    /**
     * Usuário altera alguma coisa no called já criado
     * User changes something in the already created called
     * @param called
     * @param dto
     */
    public static void applyCalled(Called called, CalledUpdateDTO dto) {

        if (dto.getTitle() != null) {
            called.setTitle(dto.getTitle());
            System.out.println("Titulo alterado !");
        }

        if (dto.getDescription() != null) {
            called.setDescription(dto.getDescription());
            System.out.println("Descrição alterada !");
        }

        if (dto.getPriority() != null) {
            called.setPriority(dto.getPriority());
            System.out.println("Prioridade alterada !");
        }
    }}
