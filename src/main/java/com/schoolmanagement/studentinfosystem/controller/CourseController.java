package com.schoolmanagement.studentinfosystem.controller;

import com.schoolmanagement.studentinfosystem.dto.CourseDTO;
import com.schoolmanagement.studentinfosystem.entity.Course;
import com.schoolmanagement.studentinfosystem.mapper.CourseMapper;
import com.schoolmanagement.studentinfosystem.repository.CourseRepository;
import com.schoolmanagement.studentinfosystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.schoolmanagement.studentinfosystem.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository; // <<< Bunu da burada inject et

    @GetMapping("/admin/courses")
    public String listCoursesForAdmin(Model model) {
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "admin/courses";
    }

    // Admin için ders ekleme formu
    @GetMapping("/admin/courses/add")
    public String showAddCourseFormForAdmin(Model model) {
        model.addAttribute("isAdmin", true); // sadece admin için
        model.addAttribute("course", new CourseDTO());
        return "admin/add_course";
    }

    // Admin için ders ekleme işlemi
    @PostMapping("/admin/courses/add")
    public String addCourseByAdmin(CourseDTO courseDTO) {
        Course course = CourseMapper.toEntity(courseDTO);
        courseRepository.save(course);
        return "redirect:/admin/courses";
    }
    //admin için ders silme işlemi
    @PostMapping("/admin/courses/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
        return "redirect:/admin/courses";
    }
    // Öğretmen için ders ekleme formu
    @GetMapping("/teacher/courses/add")
    public String showAddCourseFormForTeacher(Model model) {
        model.addAttribute("course", new CourseDTO());
        return "teacher/add_course";
    }



    // Öğretmen için ders ekleme işlemi
    @PostMapping("/teacher/courses/add")
    public String addCourseByTeacher(CourseDTO courseDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // Öğretmenin User nesnesini bul
        User teacher = userRepository.findByUsername(username).orElse(null);

        if (teacher != null) {
            Course course = CourseMapper.toEntity(courseDTO);
            course.setTeacher(teacher); // Dersi ekleyen öğretmeni setle
            course.setEnrolledCount(0); // Yeni ders açıldığı için kayıtlı öğrenci 0
            courseRepository.save(course);
        }

        return "redirect:/teacher/courses";
    }

}
