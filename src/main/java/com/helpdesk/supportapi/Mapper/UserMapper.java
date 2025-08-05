package com.helpdesk.supportapi.Mapper;

import com.helpdesk.supportapi.Dto.*;
import com.helpdesk.supportapi.Model.Entity.User;
import java.util.ArrayList;

public class UserMapper {

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getPositions());

        return userDTO;
    }

    public static UserDetailDTO toUserDetailDTO(User user) {
        UserDetailDTO userDetailDTO = new UserDetailDTO(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getPositions(),
                user.getStatus(),
                user.getCreationDate());

        return userDetailDTO;
    }

    public static User toEntityFromPublicSignup(UserPublicSignupDTO dto) {
        User user = new User(
                dto.getName(),
                dto.getPassword(),
                dto.getEmail());
        return user;
    }

    public static User toEntityFromAdmin(UserAdminCreateDTO dto) {
        User user = new User(
                dto.getName(),
                dto.getPassword(),
                dto.getEmail(),
                new ArrayList<>(dto.getPositions()),
                dto.getStatus());
        return user;
    }
}
