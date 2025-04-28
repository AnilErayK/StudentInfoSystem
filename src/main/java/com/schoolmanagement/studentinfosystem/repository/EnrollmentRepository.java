package com.schoolmanagement.studentinfosystem.repository;

import com.schoolmanagement.studentinfosystem.entity.Enrollment;
import com.schoolmanagement.studentinfosystem.entity.User;
import com.schoolmanagement.studentinfosystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudent(User student);
    Optional<Enrollment> findByStudentAndCourse(User student, Course course);
}
