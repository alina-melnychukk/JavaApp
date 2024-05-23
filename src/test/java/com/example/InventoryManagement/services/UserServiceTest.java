package com.example.InventoryManagement.services;

import com.example.InventoryManagement.entities.User;
import com.example.InventoryManagement.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    UserService service;
    UserRepository repository;

    @BeforeEach
    void setUp() {
        repository = mock(UserRepository.class);
        service = new UserService(repository);
    }

    @Test
    void registerUser() {
        User user = new User(1L, "test", "380123456789");

        service.registerUser(user);

        verify(repository).save(user);
    }

    @Test
    void listUsers() {
        List<User> users = List.of(new User(1L, "test", "380123456789"));
        when(repository.findAll()).thenReturn(users);

        List<User> result = service.listUsers();

        assertEquals(users, result);
    }

    @Test
    void deleteUser() {
        User user = new User(1L, "test", "380123456789");

        service.deleteUser(user.getID());

        verify(repository).deleteById(user.getID());
    }

    @Test
    void findById() {
        User user = new User(1L, "test", "380123456789");
        when(repository.findById(user.getID())).thenReturn(Optional.of(user));

        User result = service.findById(user.getID());

        assertEquals(user, result);
    }
}