package com.schoolmanagement.studentinfosystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTO {
    private Long courseId;
    private String courseName;
    private Integer capacity;
    private Integer enrolledCount;
    private Long teacherId;
}
