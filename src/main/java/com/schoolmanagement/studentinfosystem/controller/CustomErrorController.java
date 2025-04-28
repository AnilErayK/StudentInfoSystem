package com.schoolmanagement.studentinfosystem.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");

        if (statusCode != null && statusCode == 404) {
            // Eğer 404 hatasıysa:
            if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
                return "redirect:/dashboard"; // login olmuşsa dashboard'a
            } else {
                return "redirect:/"; // login olmamışsa login sayfasına
            }
        }

        // Diğer hatalar için login sayfasına dönelim
        return "redirect:/";
    }
}
