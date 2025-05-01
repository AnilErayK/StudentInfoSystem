package com.schoolmanagement.studentinfosystem.repository;

import com.schoolmanagement.studentinfosystem.entity.Course;
import com.schoolmanagement.studentinfosystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c JOIN FETCH c.teacher WHERE c.teacher = :teacher")
    List<Course> findByTeacherWithTeacherJoin(@Param("teacher") User teacher);
}
