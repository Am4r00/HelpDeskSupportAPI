package com.helpdesk.supportapi.service;

import com.helpdesk.supportapi.dto.users.*;
import com.helpdesk.supportapi.exceptions.EmailAlreadyUsedException;
import com.helpdesk.supportapi.exceptions.EmailNotFoundException;
import com.helpdesk.supportapi.exceptions.UserNotFoundException;
import com.helpdesk.supportapi.mapper.UserMapper;
import com.helpdesk.supportapi.model.entity.User;
import com.helpdesk.supportapi.model.enums.Position;
import com.helpdesk.supportapi.model.enums.Status;
import com.helpdesk.supportapi.repository.UserRepository;
import com.helpdesk.supportapi.service.Utils.ValidationUtils;
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
    public UserDetailDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with " + id + " not found !"));

        return UserMapper.toUserDetailDTO(user);
    }

    public List<UserDTO> findAll() {
        return mapToUserDTOList(userRepository.findAll());
    }

    public UserDTO signUpUser(UserPublicSignupDTO dto) {
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

    public UserDTO signUpAdmin(UserAdminCreateDTO dto) {
        ValidationUtils.validateNotNull(dto);

        User user = UserMapper.toEntityFromAdmin(dto);
        user.setPositions(new ArrayList<>(dto.getPositions()));

        return UserMapper.toUserDTO(createUser(user, dto.getPassword()));
    }

    public List<UserDTO> listByName(String name) {
        ValidationUtils.validateNotNull(name);

        List<User> userList = userRepository.findByNameIgnoreCase(name);
        ValidationUtils.validateListNotEmpty(userList);

        return mapToUserDTOList(userList);
    }

    public UserDetailDTO findByEmail(String email) {
        ValidationUtils.validateNotNull(email);

        User userReference = Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(() -> new EmailNotFoundException("The email: " + email + " not found !"));
        return UserMapper.toUserDetailDTO(userReference);
    }

    public List<UserDTO> findAllInactiveUsers() {
        List<User> userList = userRepository.findAllInactiveUsers();
        ValidationUtils.validateListNotEmpty(userList);
        return mapToUserDTOList(userList);
    }

    public List<UserDTO> findAllActiveUsers() {
        List<User> userList = userRepository.findAllActiveUsers();
        ValidationUtils.validateListNotEmpty(userList);
        return mapToUserDTOList(userList);
    }

    public List<UserDTO> findAllCustumerUsers() {
        List<User> userList = userRepository.findAllCustumerUsers();
        ValidationUtils.validateListNotEmpty(userList);
        return mapToUserDTOList(userList);
    }

    public List<UserDetailDTO> findAllAdminUsers() {
        List<User> userList = userRepository.findAllAdminUsers();
        ValidationUtils.validateListNotEmpty(userList);
        return mapToUserDetailDTOList(userList);
    }

    public List<UserDetailDTO> findAllSupportUsers() {
        List<User> userList = userRepository.findAllSupportUsers();
        ValidationUtils.validateListNotEmpty(userList);
        return mapToUserDetailDTOList(userList);
    }

    public UserDetailDTO updateUser(Long id, UserUpdateDTO dto) {
        ValidationUtils.validateNotNull(id, dto);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found !"));

        user.setName(dto.getName());
        user.setPositions(new ArrayList<>(dto.getPositions()));
        user.setStatus(dto.getStatus());
        user = userRepository.save(user);

        return UserMapper.toUserDetailDTO(user);

    }

    public UserDetailDTO inactiveUser(Long id) {
        ValidationUtils.validateNotNull(id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found !"));

        user.setStatus(Status.INACTIVE);
        userRepository.save(user);

        return UserMapper.toUserDetailDTO(user);
    }

    public UserDetailDTO activeUser(Long id) {
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

    private List<UserDTO> mapToUserDTOList(List<User> userList) {
        return userList.stream()
                .map(UserMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    private List<UserDetailDTO> mapToUserDetailDTOList(List<User> userList) {
        return userList.stream()
                .map(UserMapper::toUserDetailDTO)
                .collect(Collectors.toList());
    }
}
