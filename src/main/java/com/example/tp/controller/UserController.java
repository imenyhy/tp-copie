package com.example.tp.controller;

import com.example.tp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Afficher la liste des utilisateurs
    @GetMapping("/view")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users"; // Vue pour afficher les utilisateurs
    }
}
