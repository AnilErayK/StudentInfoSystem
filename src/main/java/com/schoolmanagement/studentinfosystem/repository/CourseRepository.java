package com.schoolmanagement.studentinfosystem.repository;

import com.schoolmanagement.studentinfosystem.entity.Course;
import com.schoolmanagement.studentinfosystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTeacher(User teacher);
}
