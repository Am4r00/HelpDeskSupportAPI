package com.helpdesk.supportapi.service;

import com.helpdesk.supportapi.dto.user.request.AdminCreateRequest;
import com.helpdesk.supportapi.dto.user.request.PublicSignupRequest;
import com.helpdesk.supportapi.dto.user.request.UserUpdateRequest;
import com.helpdesk.supportapi.dto.user.response.UserResponse;
import com.helpdesk.supportapi.dto.user.response.UserDetailResponse;
import com.helpdesk.supportapi.exception.business.EmailAlreadyUsedException;
import com.helpdesk.supportapi.exception.domain.EmailNotFoundException;
import com.helpdesk.supportapi.exception.domain.UserNotFoundException;
import com.helpdesk.supportapi.mapper.UserMapper;
import com.helpdesk.supportapi.model.entity.User;
import com.helpdesk.supportapi.model.enums.Position;
import com.helpdesk.supportapi.model.enums.Status;
import com.helpdesk.supportapi.repository.UserRepository;
import com.helpdesk.supportapi.shared.validation.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Acha clientes pelo ID
    public UserDetailResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with " + id + " not found !"));

        return UserMapper.toUserDetailDTO(user);
    }

    public List<UserResponse> findAll() {
        return mapToUserDTOList(userRepository.findAll());
    }

    public UserResponse signUpUser(PublicSignupRequest dto) {
        ValidationUtils.validateNotNull(dto);
        User user = UserMapper.toEntityFromPublicSignup(dto);
        User userReference = userRepository.findByEmail(user.getEmail());

        if (userReference != null) {
            throw new EmailAlreadyUsedException("The email: " + user.getEmail() + " already used !");
        }else{
            user.setPositions(List.of(Position.CUSTOMER));
            return UserMapper.toUserDTO(createUser(user, dto.getPassword()));
        }
    }

    public UserResponse signUpAdmin(AdminCreateRequest dto) {
        ValidationUtils.validateNotNull(dto);

        User user = UserMapper.toEntityFromAdmin(dto);
        user.setPositions(new ArrayList<>(dto.getPositions()));

        return UserMapper.toUserDTO(createUser(user, dto.getPassword()));
    }

    public List<UserResponse> listByName(String name) {
        ValidationUtils.validateNotNull(name);

        List<User> userList = userRepository.findByNameIgnoreCase(name);
        ValidationUtils.validateListNotEmpty(userList);

        return mapToUserDTOList(userList);
    }

    public UserDetailResponse findByEmail(String email) {
        ValidationUtils.validateNotNull(email);

        User userReference = Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(() -> new EmailNotFoundException("The email: " + email + " not found !"));
        return UserMapper.toUserDetailDTO(userReference);
    }

    public List<UserResponse> findAllInactiveUsers() {
        List<User> userList = userRepository.findAllInactiveUsers();
        ValidationUtils.validateListNotEmpty(userList);
        return mapToUserDTOList(userList);
    }

    public List<UserResponse> findAllActiveUsers() {
        List<User> userList = userRepository.findAllActiveUsers();
        ValidationUtils.validateListNotEmpty(userList);
        return mapToUserDTOList(userList);
    }

    public List<UserResponse> findAllCustumerUsers() {
        List<User> userList = userRepository.findAllCustumerUsers();
        ValidationUtils.validateListNotEmpty(userList);
        return mapToUserDTOList(userList);
    }

    public List<UserDetailResponse> findAllAdminUsers() {
        List<User> userList = userRepository.findAllAdminUsers();
        ValidationUtils.validateListNotEmpty(userList);
        return mapToUserDetailDTOList(userList);
    }

    public List<UserDetailResponse> findAllSupportUsers() {
        List<User> userList = userRepository.findAllSupportUsers();
        ValidationUtils.validateListNotEmpty(userList);
        return mapToUserDetailDTOList(userList);
    }

    public UserDetailResponse updateUser(Long id, UserUpdateRequest dto) {
        ValidationUtils.validateNotNull(id, dto);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found !"));

        user.setName(dto.getName());
        user.setPositions(new ArrayList<>(dto.getPositions()));
        user.setStatus(dto.getStatus());
        user = userRepository.save(user);

        return UserMapper.toUserDetailDTO(user);

    }

    public UserDetailResponse inactiveUser(Long id) {
        ValidationUtils.validateNotNull(id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found !"));

        user.setStatus(Status.INACTIVE);
        userRepository.save(user);

        return UserMapper.toUserDetailDTO(user);
    }

    public UserDetailResponse activeUser(Long id) {
        ValidationUtils.validateNotNull(id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found !"));

        user.setStatus(Status.ACTIVE);
        userRepository.save(user);

        return UserMapper.toUserDetailDTO(user);
    }

    public void deleteUser(Long id) {
        ValidationUtils.validateNotNull(id);
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found !");
        }
        userRepository.deleteById(id);
    }

    private String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    private User createUser(User user, String password) {
        user.setPassword(encodePassword(password));
        user.setStatus(Status.ACTIVE);

        userRepository.save(user);
        return user;
    }

    private List<UserResponse> mapToUserDTOList(List<User> userList) {
        return userList.stream()
                .map(UserMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    private List<UserDetailResponse> mapToUserDetailDTOList(List<User> userList) {
        return userList.stream()
                .map(UserMapper::toUserDetailDTO)
                .collect(Collectors.toList());
    }
}
