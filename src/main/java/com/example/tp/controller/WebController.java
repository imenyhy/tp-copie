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
import java.util.Set;

@Controller
public class WebController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    // Page d'accueil
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    // Page de login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Liste des utilisateurs
    @GetMapping("/users/all")
    public String viewUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    // Formulaire d'ajout d'utilisateur (admin)
    @GetMapping("/users/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleRepository.findAll());
        return "add-user";
    }

    // Sauvegarde utilisateur avec rôle sélectionné (admin)
    @PostMapping("/users/save")
    public String saveUser(@Valid @ModelAttribute("user") User user,
                           BindingResult result,
                           @RequestParam("selectedRole") Long roleId,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("allRoles", roleRepository.findAll());
            return "add-user";
        }

        Role selectedRole = roleRepository.findById(roleId).orElse(null);
        if (selectedRole != null) {
            Set<Role> roles = new HashSet<>();
            roles.add(selectedRole);
            user.setRoles(roles);
        }

        userService.saveUser(user);
        return "redirect:/users/all";
    }

    // Formulaire d'inscription (public)
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Traitement de l'inscription (public)
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        // Vérifie si l'email est déjà utilisé
        boolean emailExists = userService.getAllUsers().stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(user.getEmail()));
        if (emailExists) {
            model.addAttribute("error", "Cet email est déjà utilisé.");
            return "register";
        }

        // Affecter le rôle USER
        Role userRole = roleRepository.findByName("USER").orElse(null);
        if (userRole != null) {
            user.setRoles(Set.of(userRole));
        }

        userService.saveUser(user);
        return "redirect:/login?registered";
    }
}
