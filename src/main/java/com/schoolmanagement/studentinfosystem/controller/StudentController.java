package com.schoolmanagement.studentinfosystem.controller;

import com.schoolmanagement.studentinfosystem.dto.CourseDTO;
import com.schoolmanagement.studentinfosystem.entity.Course;
import com.schoolmanagement.studentinfosystem.entity.Enrollment;
import com.schoolmanagement.studentinfosystem.entity.User;
import com.schoolmanagement.studentinfosystem.mapper.CourseMapper;
import com.schoolmanagement.studentinfosystem.repository.CourseRepository;
import com.schoolmanagement.studentinfosystem.repository.EnrollmentRepository;
import com.schoolmanagement.studentinfosystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;

    @GetMapping("/student/courses")
    public String listAvailableCourses(Model model) {
        // 1) Boş kontenjanlı dersleri al
        List<Course> availableCourses = courseRepository.findAll()
                .stream()
                .filter(c -> c.getEnrolledCount() < c.getCapacity())
                .toList();

        // 2) DTO’ya çevir
        List<CourseDTO> availableCourseDTOs = availableCourses.stream()
                .map(CourseMapper::toDTO)
                .toList();

        // 3) Öğrencinin mevcut kayıtlı derslerinin ID’lerini al
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User student = userRepository.findByUsername(username).orElse(null);

        List<Long> enrolledIds = List.of();
        if (student != null) {
            enrolledIds = enrollmentRepository
                    .findByStudent(student)
                    .stream()
                    .map(enr -> enr.getCourse().getCourseId())
                    .toList();
        }

        // 4) Model’e ekle
        model.addAttribute("courses", availableCourseDTOs);
        model.addAttribute("enrolledIds", enrolledIds);

        return "student/courses";
    }

    @PostMapping("/student/courses/enroll")
    public String enrollInCourse(@RequestParam Long courseId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User student = userRepository.findByUsername(username).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (student != null && course != null && course.getEnrolledCount() < course.getCapacity()) {
            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student);
            enrollment.setCourse(course);
            enrollment.setTeacher(course.getTeacher());

            enrollmentRepository.save(enrollment);

            course.setEnrolledCount(course.getEnrolledCount() + 1);
            courseRepository.save(course);
        }

        return "redirect:/student/courses";
    }
    @GetMapping("/student/my-courses")
    public String listMyCourses(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User student = userRepository.findByUsername(username).orElse(null);

        if (student != null) {
            List<Enrollment> myEnrollments = enrollmentRepository.findByStudent(student);
            model.addAttribute("enrollments", myEnrollments);
        }

        return "student/my_courses"; // templates/student/my_courses.html
    }
    @PostMapping("/student/my-courses/drop")
    public String dropCourse(@RequestParam Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId).orElse(null);

        if (enrollment != null) {
            // Önce ilgili dersin öğrenci sayısını azalt
            Course course = enrollment.getCourse();
            course.setEnrolledCount(course.getEnrolledCount() - 1);
            courseRepository.save(course);

            // Sonra kaydı sil
            enrollmentRepository.deleteById(enrollmentId);
        }

        return "redirect:/student/my-courses";
    }

}
