package com.schoolmanagement.studentinfosystem.controller;

import com.schoolmanagement.studentinfosystem.entity.User;
import com.schoolmanagement.studentinfosystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/")
    public String home() {
        return "login"; // login.html
    }


    @GetMapping("/dashboard")
    public String dashboard() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {
            int role = user.getRole();
            if (role == 0) {
                return "admin_dashboard";
            } else if (role == 1) {
                return "teacher_dashboard";
            } else if (role == 2) {
                return "student_dashboard";
            }
        }
        return "login";
    }
}
