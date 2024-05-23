package com.example.InventoryManagement.services;

import com.example.InventoryManagement.entities.User;
import com.example.InventoryManagement.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User registerUser(User user) {
        repository.save(user);
        return user;
    }

    public List<User> listUsers() {
        return repository.findAll();
    }

    public void deleteUser(Long userId) {
       repository.deleteById(userId);
    }

    public User findById(Long userId) {
        return repository.findById(userId).orElse(null);
    }
}
