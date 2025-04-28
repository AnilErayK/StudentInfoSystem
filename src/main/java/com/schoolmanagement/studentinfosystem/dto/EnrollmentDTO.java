package com.schoolmanagement.studentinfosystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollmentDTO {
    private Long enrollmentId;
    private Long courseId;
    private Long studentId;
    private Long teacherId;

}
