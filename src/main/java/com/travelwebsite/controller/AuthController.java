package com.travelwebsite.controller;

import com.travelwebsite.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.travelwebsite.service.UserService;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Login page
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login"; // returns login.html under templates/auth/
    }

    // Register page
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "auth/register"; // returns register.html under templates/auth/
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String registerUser(User user, RedirectAttributes redirectAttributes) {
        try {
            userService.saveUser(user);
            return "redirect:/login?registered"; // redirect with success query param
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Registration failed: " + e.getMessage());
            return "redirect:/register"; // redirect back with error
        }
    }

    // Access denied page
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "auth/access-denied"; // returns access-denied.html under templates/auth/
    }
}
