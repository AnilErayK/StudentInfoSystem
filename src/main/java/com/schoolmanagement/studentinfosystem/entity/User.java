package com.schoolmanagement.studentinfosystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Lob
    @Column(name = "profile_photo")
    private byte[] profilePhoto;

    @Column(nullable = false)
    private int role; // 0 - Admin, 1 - Öğretmen, 2 - Öğrenci
}
