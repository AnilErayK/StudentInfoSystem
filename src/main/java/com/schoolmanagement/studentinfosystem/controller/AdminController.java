package com.schoolmanagement.studentinfosystem.controller;

import com.schoolmanagement.studentinfosystem.dto.CourseDTO;
import com.schoolmanagement.studentinfosystem.dto.UserDTO;
import com.schoolmanagement.studentinfosystem.entity.Course;
import com.schoolmanagement.studentinfosystem.entity.User;
import com.schoolmanagement.studentinfosystem.mapper.CourseMapper;
import com.schoolmanagement.studentinfosystem.mapper.UserMapper;
import com.schoolmanagement.studentinfosystem.repository.CourseRepository;
import com.schoolmanagement.studentinfosystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @GetMapping("/admin/users")
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = users.stream()
                .map(UserMapper::toDTO)
                .toList();
        model.addAttribute("users", userDTOs);
        return "admin/users";
    }

    @GetMapping("/admin/users/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "admin/add_user";
    }

    @PostMapping("/admin/users/add")
    public String addUser(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        userRepository.save(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        System.out.println("Siliniyor: " + id);
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }
    @GetMapping("/admin/users/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            model.addAttribute("user", user);
            return "admin/edit_user"; // admin/edit_user.html
        }

        return "redirect:/admin/users";
    }

    @PostMapping("/admin/users/edit")
    public String updateUser(User user) {
        userRepository.save(user); // User nesnesini kaydet (ID varsa update yapar)
        return "redirect:/admin/users";
    }

}
