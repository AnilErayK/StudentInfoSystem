package com.schoolmanagement.studentinfosystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String username;
    private String password;
    private byte[] profilePhoto;
    private int role;
}
