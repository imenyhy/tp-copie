package com.example.tp;

import com.example.tp.entity.Role;
import com.example.tp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        // Vérifier si les rôles existent déjà
        if (roleRepository.count() == 0) {
            // Ajouter des rôles par défaut
            Role userRole = new Role();
            userRole.setName("USER");
            roleRepository.save(userRole);

            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);
        }
    }
}
