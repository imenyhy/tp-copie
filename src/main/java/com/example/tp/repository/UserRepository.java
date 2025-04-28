package com.example.tp.repository;

import com.example.tp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // Méthode ajoutée pour récupérer un utilisateur par son email
}
