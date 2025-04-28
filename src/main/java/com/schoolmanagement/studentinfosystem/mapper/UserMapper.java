package com.schoolmanagement.studentinfosystem.mapper;

import com.schoolmanagement.studentinfosystem.dto.UserDTO;
import com.schoolmanagement.studentinfosystem.entity.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setProfilePhoto(user.getProfilePhoto());
        dto.setRole(user.getRole());
        return dto;
    }

    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setProfilePhoto(dto.getProfilePhoto());
        user.setRole(dto.getRole());
        return user;
    }
}
