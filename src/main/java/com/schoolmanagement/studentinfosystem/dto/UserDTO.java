package com.schoolmanagement.studentinfosystem.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserDTO {
    private Long userId;
    private String username;
    private String password;
    private MultipartFile photoFile;
    private byte[] photo;
    private boolean hasPhoto;
    private int role;


}
