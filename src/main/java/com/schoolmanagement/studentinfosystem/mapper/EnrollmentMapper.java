package com.schoolmanagement.studentinfosystem.mapper;

import com.schoolmanagement.studentinfosystem.dto.EnrollmentDTO;
import com.schoolmanagement.studentinfosystem.entity.Enrollment;

public class EnrollmentMapper {

    public static EnrollmentDTO toDTO(Enrollment enrollment) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setEnrollmentId(enrollment.getEnrollmentId());
        dto.setCourseId(enrollment.getCourse().getCourseId());
        dto.setStudentId(enrollment.getStudent().getUserId());
        dto.setTeacherId(enrollment.getTeacher().getUserId());
        return dto;
    }

    public static Enrollment toEntity(EnrollmentDTO dto) {
        Enrollment enrollment = new Enrollment();
        // Entity nesneleri (Course, Student, Teacher) relation olarak çekilip set edilecek.
        return enrollment;
    }
}
