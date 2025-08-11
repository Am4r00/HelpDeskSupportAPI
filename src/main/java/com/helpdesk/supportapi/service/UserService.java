package com.helpdesk.supportapi.service;

import com.helpdesk.supportapi.dto.users.*;
import com.helpdesk.supportapi.exceptions.EmailAlreadyUsedException;
import com.helpdesk.supportapi.exceptions.EmailNotFound;
import com.helpdesk.supportapi.exceptions.UserNotFoundException;
import com.helpdesk.supportapi.mapper.UserMapper;
import com.helpdesk.supportapi.model.entity.User;
import com.helpdesk.supportapi.model.enums.Position;
import com.helpdesk.supportapi.model.enums.Status;
import com.helpdesk.supportapi.repository.UserRepository;
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

    public UserDetailDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with " + id + " not found !"));

        return UserMapper.toUserDetailDTO(user);
    }

    public List<UserDTO> findAll() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            throw new UserNotFoundException("None User were found !");
        }
        return mapToUserDTOList(userList);
    }

    public UserDTO signUpUser(UserPublicSignupDTO dto) {

        User user = UserMapper.toEntityFromPublicSignup(dto);

        User userReference = userRepository.findByEmail(user.getEmail());

        if (userReference == null) {
            user.setPositions(List.of(Position.CUSTOMER));
            return UserMapper.toUserDTO(createUser(user, dto.getPassword()));
        }
        throw new EmailAlreadyUsedException("The email: " + user.getEmail() + " already used !");
    }

    public UserDTO signUpAdmin(UserAdminCreateDTO dto) {

        User user = UserMapper.toEntityFromAdmin(dto);
        user.setPositions(new ArrayList<>(dto.getPositions()));

        return UserMapper.toUserDTO(createUser(user, dto.getPassword()));
    }

    public List<UserDTO> listByName(String name) {
        List<User> userList = userRepository.findByNameIgnoreCase(name);
        if (userList.isEmpty()) {
            throw new UserNotFoundException("No User with the name: " + name + " was found !");
        }
        return mapToUserDTOList(userList);
    }

    public UserDetailDTO findByEmail(String email) {
        User userReference = Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(() -> new EmailNotFound("The email: " + email + " not found !"));

        return UserMapper.toUserDetailDTO(userReference);
    }

    public List<UserDTO> findAllInactiveUsers() {
        List<User> userList = userRepository.findAllInactiveUsers();
        if (userList.isEmpty()) {
            throw new UserNotFoundException("No user inactive was found !");
        }
        return mapToUserDTOList(userList);
    }

    public List<UserDTO> findAllActiveUsers() {
        List<User> userList = userRepository.findAllActiveUsers();
        if (userList.isEmpty()) {
            throw new UserNotFoundException("No user Active was found !");
        }
        return mapToUserDTOList(userList);
    }

    public List<UserDTO> findAllCustumerUsers() {
        List<User> userList = userRepository.findAllCustumerUsers();
        if (userList.isEmpty()) {
            throw new UserNotFoundException("No Customer was found !");
        }
        return mapToUserDTOList(userList);
    }

    public List<UserDetailDTO> findAllAdminUsers() {
        List<User> userList = userRepository.findAllAdminUsers();
        if (userList.isEmpty()) {
            throw new UserNotFoundException("No Admin was found !");
        }
        return mapToUserDetailDTOList(userList);
    }

    public List<UserDetailDTO> findAllSupportUsers() {
        List<User> userList = userRepository.findAllSupportUsers();
        if (userList.isEmpty()) {
            throw new UserNotFoundException("No Support was found !");
        }
        return mapToUserDetailDTOList(userList);
    }

    public UserDetailDTO updateUser(Long id, UserUpdateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found !"));

        user.setName(dto.getName());
        user.setPositions(new ArrayList<>(dto.getPositions()));
        user.setStatus(dto.getStatus());
        user = userRepository.save(user);

        return UserMapper.toUserDetailDTO(user);

    }

    public UserDetailDTO inactiveUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found !"));

        user.setStatus(Status.INACTIVE);
        userRepository.save(user);

        return UserMapper.toUserDetailDTO(user);
    }

    public UserDetailDTO activeUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found !"));

        user.setStatus(Status.ACTIVE);
        userRepository.save(user);

        return UserMapper.toUserDetailDTO(user);
    }

    public void deleteUser(Long id){
        if(!userRepository.existsById(id)){
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
