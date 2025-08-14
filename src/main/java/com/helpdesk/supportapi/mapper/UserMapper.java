package com.helpdesk.supportapi.mapper;

import com.helpdesk.supportapi.dto.users.*;
import com.helpdesk.supportapi.model.entity.User;

import java.util.ArrayList;

public class UserMapper {

    /**
     * DTO para expor apenas dados necessários do user.
     * DTO to expose only necessary user data.
     *
     * @param user
     * @return
     */
    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getPositions());

        return userDTO;
    }

    /**
     * DTO que expoe tudo menos dados sensiveis do usuário.
     * DTO that exposes everything except sensitive user data.
     *
     * @param user
     * @return
     */
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

    /**
     * Aplica novas atualizações ao usuário por meio de um dto
     *
     * @param user
     * @param dto
     */
    public static void upDateUser(User user, UserUpdateDTO dto) {
        if (dto.getName() != null) {
            user.setName(dto.getName());
        }

        if (dto.getPositions() != null) {
            user.setPositions(new ArrayList<>(dto.getPositions()));
        }

        if (dto.getStatus() != null) {
            user.setStatus(dto.getStatus());
        }
    }

    /**
     * Converte um userPublicSingUpDTO criado pelo usuario em User
     * Converts a userPublicSingUpDTO created by the user to User
     *
     * @param dto
     * @return
     */
    public static User toEntityFromPublicSignup(UserPublicSignupDTO dto) {
        User user = new User(
                dto.getName(),
                dto.getPassword(),
                dto.getEmail());
        return user;
    }

    /**
     * Converte um UserAdminCreateDTO criado pelo Admin em user
     * Converts a UserAdminCreateDTO created by the Admin to User
     *
     * @param dto
     * @return
     */
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
