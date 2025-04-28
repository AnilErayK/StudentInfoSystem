package com.schoolmanagement.studentinfosystem.service;

import com.schoolmanagement.studentinfosystem.entity.Enrollment;
import com.schoolmanagement.studentinfosystem.entity.User;
import com.schoolmanagement.studentinfosystem.entity.Course;
import com.schoolmanagement.studentinfosystem.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public List<Enrollment> getEnrollmentsByStudent(User student) {
        return enrollmentRepository.findByStudent(student);
    }

    public Optional<Enrollment> findByStudentAndCourse(User student, Course course) {
        return enrollmentRepository.findByStudentAndCourse(student, course);
    }

    public Enrollment save(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }
}
