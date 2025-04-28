package com.example.tp.repository;

import com.example.tp.entity.Role; // ← 🔴 Il manquait cet import
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
