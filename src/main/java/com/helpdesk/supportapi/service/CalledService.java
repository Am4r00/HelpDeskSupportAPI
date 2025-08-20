package com.helpdesk.supportapi.service;

import com.helpdesk.supportapi.dto.called.CalledCreateDTO;
import com.helpdesk.supportapi.dto.called.CalledDTO;
import com.helpdesk.supportapi.dto.called.CalledDetailDTO;
import com.helpdesk.supportapi.dto.called.CalledUpdateDTO;
import com.helpdesk.supportapi.exceptions.*;
import com.helpdesk.supportapi.mapper.CalledMapper;
import com.helpdesk.supportapi.model.entity.CallHistory;
import com.helpdesk.supportapi.model.entity.Called;
import com.helpdesk.supportapi.model.entity.Category;
import com.helpdesk.supportapi.model.entity.User;
import com.helpdesk.supportapi.model.enums.Position;
import com.helpdesk.supportapi.model.enums.Priority;
import com.helpdesk.supportapi.model.enums.StatusCalled;
import com.helpdesk.supportapi.repository.CalledRepository;
import com.helpdesk.supportapi.repository.CategoryRepository;
import com.helpdesk.supportapi.repository.UserRepository;
import com.helpdesk.supportapi.service.Utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalledService {

    private final CalledRepository calledRepository;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    private final CallHistoryService callHistoryService;

    @Autowired
    public CalledService(CalledRepository calledRepository, CategoryRepository categoryRepository, UserRepository userRepository, CallHistoryService callHistoryService) {
        this.calledRepository = calledRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.callHistoryService = callHistoryService;
    }

    //Criando Called
    public CalledDetailDTO createCalled(CalledCreateDTO dto, Category category, User userCreator) {
        ValidationUtils.validateNotNull(dto, category, userCreator);
        Category entityCategory = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found or ID invalid"));

        Called called = new Called();

        called.setTitle(dto.getTitle());
        called.setDescription(dto.getDescription());
        called.setCategory(entityCategory);
        called.setUser(userCreator);
        called.setPriority(entityCategory.getDefaultPriority());
        called.setStatus(StatusCalled.OPEN);
        called.setCallHistory(new ArrayList<>());

        calledRepository.save(called);

        return CalledMapper.toCalledDetailDTO(called);
    }

    //Designando responsável
    public CalledDTO designateResponsible(CalledDTO dto, User userResponsible) {
        ValidationUtils.validateNotNull(dto, userResponsible);
        if (!userResponsible.getPositions().contains(Position.SUPPORT)) {
            throw new UserWithoutPermissionException("User does not have permission to perform this type of task");
        } else {
            Called called = calledRepository.findById(dto.getId())
                    .orElseThrow(() -> new CalledNotFoundException("Called not found !"));

            called.setResponsibleUser(userResponsible);
            called.setUpdateDate();
            registerHistory(userResponsible, called, "User: " + userResponsible.getName() + " has been assigned to be responsible for call " + dto.getId());

            calledRepository.save(called);

            return CalledMapper.toCalledDTO(called);
        }
    }


    public CalledDTO updateCalled(CalledUpdateDTO dto, Called called, User userResponsible) {
        ValidationUtils.validateNotNull(dto, called, userResponsible);
        Category newCategory = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found or ID invalid"));

        called.setTitle(dto.getTitle());
        called.setDescription(dto.getDescription());
        called.setCategory(newCategory);
        called.setPriority(newCategory.getDefaultPriority());
        registerHistory(userResponsible, called, "The called with ID: " + called.getId() + " had its information changed by " + userResponsible.getName());

        calledRepository.save(called);

        return CalledMapper.toCalledDTO(called);

    }

    public void deleteCalled(CalledDTO dto) {
        ValidationUtils.validateNotNull(dto);
        Called called = calledRepository.findById(dto.getId())
                .orElseThrow(() -> new CalledNotFoundException("Called not found !"));
        calledRepository.deleteById(called.getId());
        System.out.println("call deleted successfully !");
    }

    //Buscando apenas um Called com detalhes
    public CalledDetailDTO findByIdCalledDetail(Long id) {
        ValidationUtils.validateNotNull(id);
        Called called = calledRepository.findById(id)
                .orElseThrow(() -> new CalledNotFoundException("Called not found or ID invalid"));
        return CalledMapper.toCalledDetailDTO(called);
    }

    // busca um called de um usuário especifico  !! revisar RN pode ser inválida
    public CalledDTO findByIdCalledDTO(Long id, User user) {
        ValidationUtils.validateNotNull(id, user);
        Called called = calledRepository.findById(id)
                .orElseThrow(() -> new CalledNotFoundException("Called not found or ID invalid"));
        if (called.getUser().equals(user)) {
            return CalledMapper.toCalledDTO(called);
        } else {
            throw new UserWithoutPermissionException("This called does not is yours");
        }

    }

    public List<CalledDTO> toCalledDTOList() {
        return CalledMapper.toCalledDTOList(calledRepository.findAll());
    }

    public List<CalledDTO> findAllByUser_IdAndStatus(User user) {
        ValidationUtils.validateNotNull(user);
        return CalledMapper.toCalledDTOList(calledRepository.findAllByUserId(user.getId()));

    }

    public List<CalledDTO> findAllByUser_IdAndStatus(User user, StatusCalled status) {
        ValidationUtils.validateNotNull(user, status);
        return CalledMapper.toCalledDTOList(calledRepository.findAllByUser_IdAndStatus(user.getId(), status));
    }

    public List<CalledDTO> findAllByPriority(Priority priority) {
        ValidationUtils.validateNotNull(priority);
        return CalledMapper.toCalledDTOList(calledRepository.findAllByPriority(priority));
    }

    public List<CalledDTO> findAllByPriorityIn(List<Priority> prioritiesList) {
        ValidationUtils.validateListNotEmpty(prioritiesList);
        return CalledMapper.toCalledDTOList(calledRepository.findAllByPriorityIn(prioritiesList));
    }

    public List<CalledDTO> listByStatusCalled(StatusCalled status) {
        ValidationUtils.validateNotNull(status);
        return CalledMapper.toCalledDTOList(calledRepository.findAllByStatus(status));
    }

    public List<CalledDTO> findAllByStatusIn(List<StatusCalled> statusList) {
        ValidationUtils.validateListNotEmpty(statusList);
        return CalledMapper.toCalledDTOList(calledRepository.findAllByStatusIn(statusList));
    }

    public List<CalledDTO> listByCategoryCalled(Category category) {
        ValidationUtils.validateNotNull(category);
        return CalledMapper.toCalledDTOList(calledRepository.findByCategoryIgnoreCase(category.getName()));
    }

    private void registerHistory(User user, Called called, String message) {
        CallHistory history = callHistoryService.createCallHistory(user, called, message);
        called.getCallHistory().add(history);
    }
}
