package com.example.tp.repository;

import com.example.tp.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void testFindByName() {
        Role role = new Role("TEST_ROLE");
        roleRepository.save(role);

        Optional<Role> result = roleRepository.findByName("TEST_ROLE");
        assertTrue(result.isPresent());
        assertEquals("TEST_ROLE", result.get().getName());
    }
}
