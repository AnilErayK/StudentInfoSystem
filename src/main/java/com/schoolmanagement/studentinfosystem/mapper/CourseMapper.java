package com.schoolmanagement.studentinfosystem.mapper;

import com.schoolmanagement.studentinfosystem.dto.CourseDTO;
import com.schoolmanagement.studentinfosystem.entity.Course;
import com.schoolmanagement.studentinfosystem.entity.User;

public class CourseMapper {

    public static CourseDTO toDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setCourseId(course.getCourseId());
        dto.setCourseName(course.getCourseName());
        dto.setCapacity(course.getCapacity());
        dto.setEnrolledCount(course.getEnrolledCount());
        dto.setTeacherId(course.getTeacher().getUserId()); // teacher bir User objesi
        return dto;
    }

    public static Course toEntity(CourseDTO dto) {
        Course course = new Course();
        course.setCourseId(dto.getCourseId());
        course.setCourseName(dto.getCourseName());
        course.setCapacity(dto.getCapacity());
        course.setEnrolledCount(dto.getEnrolledCount());
        // teacher alanı için User nesnesi sonra set edilir (id'den çekip)
        return course;
    }
}
