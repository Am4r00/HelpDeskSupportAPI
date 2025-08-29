package com.helpdesk.supportapi.mapper;

import com.helpdesk.supportapi.dto.user.request.AdminCreateRequest;
import com.helpdesk.supportapi.dto.user.request.PublicSignupRequest;
import com.helpdesk.supportapi.dto.user.request.UserUpdateRequest;
import com.helpdesk.supportapi.dto.user.response.UserResponse;
import com.helpdesk.supportapi.dto.user.response.UserDetailResponse;
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
    public static UserResponse toUserDTO(User user) {
        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getPositions());

        return userResponse;
    }

    /**
     * DTO que expoe tudo menos dados sensiveis do usuário.
     * DTO that exposes everything except sensitive user data.
     *
     * @param user
     * @return
     */
    public static UserDetailResponse toUserDetailDTO(User user) {
        UserDetailResponse userDetailResponse = new UserDetailResponse(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getPositions(),
                user.getStatus(),
                user.getCreationDate());

        return userDetailResponse;
    }

    /**
     * Aplica novas atualizações ao usuário por meio de um dto
     *
     * @param user
     * @param dto
     */
    public static void upDateUser(User user, UserUpdateRequest dto) {
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
    public static User toEntityFromPublicSignup(PublicSignupRequest dto) {
        User user = new User(
                dto.getName(),
                dto.getPassword(),
                dto.getEmail());
        return user;
    }

    /**
     * Converte um AdminCreateRequest criado pelo Admin em user
     * Converts a AdminCreateRequest created by the Admin to User
     *
     * @param dto
     * @return
     */
    public static User toEntityFromAdmin(AdminCreateRequest dto) {
        User user = new User(
                dto.getName(),
                dto.getPassword(),
                dto.getEmail(),
                new ArrayList<>(dto.getPositions()),
                dto.getStatus());
        return user;
    }


}
