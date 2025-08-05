package com.helpdesk.supportapi.Service;

import com.helpdesk.supportapi.Dto.UserDTO;
import com.helpdesk.supportapi.Dto.UserPublicSignupDTO;
import com.helpdesk.supportapi.Mapper.UserMapper;
import com.helpdesk.supportapi.Model.Entity.User;
import com.helpdesk.supportapi.Model.Enums.Position;
import com.helpdesk.supportapi.Model.Enums.Status;
import com.helpdesk.supportapi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserDTO SingUpUser(UserPublicSignupDTO dto){

        User user = UserMapper.toEntityFromPublicSignup(dto);
        String passwordCrypt = passwordEncoder.encode(dto.getPassword());
        user.setPassword(passwordCrypt);

        user.setPositions(List.of(Position.CUSTOMER));
        user.setStatus(Status.ACTIVE);

        userRepository.save(user);

        UserDTO userDTO = UserMapper.toUserDTO(user);

        return userDTO;

    }
}
