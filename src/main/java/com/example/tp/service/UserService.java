package com.example.tp.service;

import com.example.tp.entity.User;
import com.example.tp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Récupérer tous les utilisateurs
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Sauvegarder un utilisateur
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Récupérer un utilisateur par ID (optionnel pour l'API)
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Supprimer un utilisateur par ID (optionnel pour l'API)
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
