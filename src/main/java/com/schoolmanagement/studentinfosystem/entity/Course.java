package com.schoolmanagement.studentinfosystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false)
    private String courseName;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher; // Dersi veren öğretmen

    @Column(nullable = false)
    private int studentCount = 0; // Bu dersi alan öğrenci sayısı

    @Column(nullable = false)
    private int quota; // Kontenjan
}
