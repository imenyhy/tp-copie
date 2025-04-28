package com.example.tp.controller;

import com.example.tp.entity.User;
import com.example.tp.entity.Role;
import com.example.tp.repository.RoleRepository;
import com.example.tp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
public class WebController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    // Page d'accueil
    @GetMapping("/home")
    public String home() {
        return "home"; // Vue d'accueil
    }

    // Page de login
    @GetMapping("/login")
    public String login() {
        return "login"; // Vue de login
    }

    // Affichage des utilisateurs (nouvelle route)
    @GetMapping("/users/all")
    public String viewUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users"; // Vue de la liste des utilisateurs
    }

    // Formulaire d'ajout d'utilisateur
    @GetMapping("/users/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleRepository.findAll());
        return "add-user"; // Vue pour ajouter un utilisateur
    }

    // Sauvegarde de l'utilisateur avec le rôle
    @PostMapping("/users/save")
    public String saveUser(@Valid @ModelAttribute("user") User user,
                           BindingResult result,
                           @RequestParam("selectedRole") Long roleId,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("allRoles", roleRepository.findAll());
            return "add-user"; // Retourner au formulaire en cas d'erreur
        }

        // Assigner un rôle à l'utilisateur
        Role selectedRole = roleRepository.findById(roleId).orElse(null);
        if (selectedRole != null) {
            user.setRoles(new HashSet<>());
            user.getRoles().add(selectedRole); // Ajouter le rôle sélectionné
        }

        userService.saveUser(user); // Sauvegarder l'utilisateur
        return "redirect:/users/all"; // Rediriger vers la vue des utilisateurs
    }
}
