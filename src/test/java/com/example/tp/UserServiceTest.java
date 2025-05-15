package com.example.tp.service;

import com.example.tp.entity.User;
import com.example.tp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        User mockUser = new User("Majdi", "majdi@test.com", "pass");
        when(userRepository.findAll()).thenReturn(List.of(mockUser));

        List<User> users = userService.getAllUsers();

        assertEquals(1, users.size());
        assertEquals("Majdi", users.get(0).getName());
        assertEquals("majdi@test.com", users.get(0).getEmail());
    }
}
