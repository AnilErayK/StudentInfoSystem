package com.schoolmanagement.studentinfosystem.mapper;

import com.schoolmanagement.studentinfosystem.dto.UserDTO;
import com.schoolmanagement.studentinfosystem.entity.User;

import java.util.Base64;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setHasPhoto(user.getPhoto() != null);
        dto.setRole(user.getRole());
        return dto;
    }

    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setPhoto(dto.getPhoto());
        user.setRole(dto.getRole());
        return user;
    }
}
