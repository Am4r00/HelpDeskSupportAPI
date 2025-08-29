package com.helpdesk.supportapi.service;

import com.helpdesk.supportapi.dto.called.request.CreateTicketRequest;
import com.helpdesk.supportapi.dto.called.response.TicketResponse;
import com.helpdesk.supportapi.dto.called.response.TicketDetailResponse;
import com.helpdesk.supportapi.dto.called.request.UpdateTicketRequest;
import com.helpdesk.supportapi.exception.business.UserWithoutPermissionException;
import com.helpdesk.supportapi.exception.domain.CategoryNotFoundException;
import com.helpdesk.supportapi.exception.domain.TicketNotFoundException;
import com.helpdesk.supportapi.mapper.CalledMapper;
import com.helpdesk.supportapi.model.entity.TicketHistory;
import com.helpdesk.supportapi.model.entity.Ticket;
import com.helpdesk.supportapi.model.entity.Category;
import com.helpdesk.supportapi.model.entity.User;
import com.helpdesk.supportapi.model.enums.Position;
import com.helpdesk.supportapi.model.enums.Priority;
import com.helpdesk.supportapi.model.enums.StatusTicket;
import com.helpdesk.supportapi.repository.TicketRepository;
import com.helpdesk.supportapi.repository.CategoryRepository;
import com.helpdesk.supportapi.repository.UserRepository;
import com.helpdesk.supportapi.shared.validation.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    private final TicketHistoryService ticketHistoryService;

    @Autowired
    public TicketService(TicketRepository ticketRepository, CategoryRepository categoryRepository, UserRepository userRepository, TicketHistoryService ticketHistoryService) {
        this.ticketRepository = ticketRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.ticketHistoryService = ticketHistoryService;
    }

    //Criando Ticket
    public TicketDetailResponse createCalled(CreateTicketRequest dto, Category category, User userCreator) {
        ValidationUtils.validateNotNull(dto, category, userCreator);
        Category entityCategory = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found or ID invalid"));

        Ticket ticket = new Ticket();

        ticket.setTitle(dto.getTitle());
        ticket.setDescription(dto.getDescription());
        ticket.setCategory(entityCategory);
        ticket.setUser(userCreator);
        ticket.setPriority(entityCategory.getDefaultPriority());
        ticket.setStatus(StatusTicket.OPEN);
        ticket.setCallHistory(new ArrayList<>());

        ticketRepository.save(ticket);

        return CalledMapper.toCalledDetailDTO(ticket);
    }

    //Designando responsável
    public TicketResponse designateResponsible(TicketResponse dto, User userResponsible) {
        ValidationUtils.validateNotNull(dto, userResponsible);
        if (!userResponsible.getPositions().contains(Position.SUPPORT)) {
            throw new UserWithoutPermissionException("User does not have permission to perform this type of task");
        } else {
            Ticket ticket = ticketRepository.findById(dto.getId())
                    .orElseThrow(() -> new TicketNotFoundException("Ticket not found !"));

            ticket.setResponsibleUser(userResponsible);
            ticket.setUpdateDate();
            registerHistory(userResponsible, ticket, "User: " + userResponsible.getName() + " has been assigned to be responsible for call " + dto.getId());

            ticketRepository.save(ticket);

            return CalledMapper.toCalledDTO(ticket);
        }
    }


    public TicketResponse updateCalled(UpdateTicketRequest dto, Ticket ticket, User userResponsible) {
        ValidationUtils.validateNotNull(dto, ticket, userResponsible);
        Category newCategory = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found or ID invalid"));

        ticket.setTitle(dto.getTitle());
        ticket.setDescription(dto.getDescription());
        ticket.setCategory(newCategory);
        ticket.setPriority(newCategory.getDefaultPriority());
        registerHistory(userResponsible, ticket, "The ticket with ID: " + ticket.getId() + " had its information changed by " + userResponsible.getName());

        ticketRepository.save(ticket);

        return CalledMapper.toCalledDTO(ticket);

    }

    public void deleteCalled(TicketResponse dto) {
        ValidationUtils.validateNotNull(dto);
        Ticket ticket = ticketRepository.findById(dto.getId())
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found !"));
        ticketRepository.deleteById(ticket.getId());
        System.out.println("call deleted successfully !");
    }

    //Buscando apenas um Ticket com detalhes
    public TicketDetailResponse findByIdCalledDetail(Long id) {
        ValidationUtils.validateNotNull(id);
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found or ID invalid"));
        return CalledMapper.toCalledDetailDTO(ticket);
    }

    // busca um called de um usuário especifico
    public TicketResponse findByIdCalledDTO(Long id, User user) {
        ValidationUtils.validateNotNull(id, user);
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found or ID invalid"));
        if (ticket.getUser().equals(user)) {
            return CalledMapper.toCalledDTO(ticket);
        } else {
            throw new UserWithoutPermissionException("This ticket does not is yours");
        }

    }

    public List<TicketResponse> toCalledDTOList() {
        return CalledMapper.toCalledDTOList(ticketRepository.findAll());
    }

    public List<TicketResponse> findAllByUser_IdAndStatus(User user) {
        ValidationUtils.validateNotNull(user);
        return CalledMapper.toCalledDTOList(ticketRepository.findAllByUserId(user.getId()));

    }

    public List<TicketResponse> findAllByUser_IdAndStatus(User user, StatusTicket status) {
        ValidationUtils.validateNotNull(user, status);
        return CalledMapper.toCalledDTOList(ticketRepository.findAllByUser_IdAndStatus(user.getId(), status));
    }

    public List<TicketResponse> findAllByPriority(Priority priority) {
        ValidationUtils.validateNotNull(priority);
        return CalledMapper.toCalledDTOList(ticketRepository.findAllByPriority(priority));
    }

    public List<TicketResponse> findAllByPriorityIn(List<Priority> prioritiesList) {
        ValidationUtils.validateListNotEmpty(prioritiesList);
        return CalledMapper.toCalledDTOList(ticketRepository.findAllByPriorityIn(prioritiesList));
    }

    public List<TicketResponse> listByStatusCalled(StatusTicket status) {
        ValidationUtils.validateNotNull(status);
        return CalledMapper.toCalledDTOList(ticketRepository.findAllByStatus(status));
    }

    public List<TicketResponse> findAllByStatusIn(List<StatusTicket> statusList) {
        ValidationUtils.validateListNotEmpty(statusList);
        return CalledMapper.toCalledDTOList(ticketRepository.findAllByStatusIn(statusList));
    }

    public List<TicketResponse> listByCategoryCalled(Category category) {
        ValidationUtils.validateNotNull(category);
        return CalledMapper.toCalledDTOList(ticketRepository.findByCategoryIgnoreCase(category.getName()));
    }

    private void registerHistory(User user, Ticket ticket, String message) {
        TicketHistory history = ticketHistoryService.createCallHistory(user, ticket, message);
        ticket.getCallHistory().add(history);
    }
}
