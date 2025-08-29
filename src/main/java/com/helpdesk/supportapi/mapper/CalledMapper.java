package com.helpdesk.supportapi.mapper;

import com.helpdesk.supportapi.dto.called.request.CreateTicketRequest;
import com.helpdesk.supportapi.dto.called.request.UpdateTicketRequest;
import com.helpdesk.supportapi.dto.called.response.TicketResponse;
import com.helpdesk.supportapi.dto.called.response.TicketDetailResponse;
import com.helpdesk.supportapi.dto.called.response.UserSummaryResponse;
import com.helpdesk.supportapi.dto.category.CategorySummaryResponse;
import com.helpdesk.supportapi.model.entity.Ticket;
import com.helpdesk.supportapi.model.entity.Category;
import com.helpdesk.supportapi.model.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class CalledMapper {

    /**
     * Criar um DTO apenas para expor o necessário do chamado
     * Create a DTO just to expose what is necessary for the call
     * @param ticket
     * @return TicketResponse resumido
     */
    public static TicketResponse toCalledDTO(Ticket ticket) {
        if (ticket == null) {
            return null;
        } else {
            TicketResponse ticketResponse = new TicketResponse();

            //Criando sem argumentos para evitar telescoping

            ticketResponse.setId(ticket.getId());
            ticketResponse.setTitle(ticket.getTitle());
            ticketResponse.setPriority(ticket.getPriority());
            ticketResponse.setStatus(ticket.getStatus());
            ticketResponse.setUpdateDate(ticket.getUpdateDate());

            return ticketResponse;
        }
    }

    /**
     * Converter um DTO para calls
     * Convert a DTO to calls
     * @param dto
     * @return Ticket
     */
    public static Ticket toCalled(TicketResponse dto){
        if(dto == null){
            return null;
        }else{
            Ticket ticket = new Ticket();
            ticket.setId(dto.getId());
            ticket.setTitle(dto.getTitle());
            ticket.setPriority(dto.getPriority());
            ticket.setUpdateDate();
            return ticket;
        }
    }

    /**
     * Criando um DTO para o ticket, expondo todos os dados
     * Creating a DTO for the ticket, exposing all data
     * @param ticket
     * @return o ticket
     */
    public static TicketDetailResponse toCalledDetailDTO(Ticket ticket) {

        if (ticket == null) {
            return null;
        } else {
            TicketDetailResponse ticketDetailResponse = new TicketDetailResponse();

            //Criando sem argumentos para evitar telescoping

            ticketDetailResponse.setId(ticket.getId());
            ticketDetailResponse.setTitle(ticket.getTitle());
            ticketDetailResponse.setDescription(ticket.getDescription());
            ticketDetailResponse.setPriority(ticket.getPriority());
            ticketDetailResponse.setStatus(ticket.getStatus());
            ticketDetailResponse.setRequester(toUserMinDTO(ticket.getUser()));
            ticketDetailResponse.setCategory(toCategoryMinDTO(ticket.getCategory()));
            ticketDetailResponse.setResponsible(toUserMinDTO(ticket.getResponsibleUser()));
            ticketDetailResponse.setCreationDate(ticket.getCreationDate());
            ticketDetailResponse.setUpdateDate(ticket.getUpdateDate());

            return ticketDetailResponse;
        }
    }

    /**
     * Criando um DTO para expor apenas o necessário do user no Ticket.
     * Creating a DTO to expose only what the user needs in Ticket.
     * @param user
     * @return usuário sem dados desnecessários
     */
    public static UserSummaryResponse toUserMinDTO(User user) {
        if (user == null) {
            return null;
        } else {
            return new UserSummaryResponse(
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
    public static Ticket fromCreateDTO(CreateTicketRequest dto, Category category, User userRequester) {
        if (dto == null || category == null || userRequester == null) {
            return null;
        } else {
            Ticket ticket = new Ticket();
            ticket.setTitle(dto.getTitle());
            ticket.setDescription(dto.getDescription());
            ticket.setCategory(category);
            ticket.setUser(userRequester);

            return ticket;

        }
    }

    /**
     * retorna apenas o nescessário da categorio
     * returns only what is necessary for the category
     * @param category
     * @return category resumida
     */
    public static CategorySummaryResponse toCategoryMinDTO(Category category) {
        if (category == null) {
            return null;
        } else {
            return new CategorySummaryResponse(
                    category.getId(),
                    category.getName());
        }
    }

    /**
     * Faz uma lista de todos os chamados
     * Make a list of all calls
     * @param ticketList
     * @return retorna uma lista dos calleds
     */
    public static List<TicketResponse> toCalledDTOList(List<Ticket> ticketList) {
        return ticketList.stream()
                .map(CalledMapper::toCalledDTO)
                .collect(Collectors.toList());

    }

    /**
     * Usuário altera alguma coisa no ticket já criado
     * User changes something in the already created ticket
     * @param ticket
     * @param dto
     */
    public static void applyCalled(Ticket ticket, UpdateTicketRequest dto) {

        if (dto.getTitle() != null) {
            ticket.setTitle(dto.getTitle());
            System.out.println("Titulo alterado !");
        }

        if (dto.getDescription() != null) {
            ticket.setDescription(dto.getDescription());
            System.out.println("Descrição alterada !");
        }

        if (dto.getPriority() != null) {
            ticket.setPriority(dto.getPriority());
            System.out.println("Prioridade alterada !");
        }
    }}
