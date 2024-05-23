package com.example.InventoryManagement.controllers.api;

import com.example.InventoryManagement.entities.User;
import com.example.InventoryManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersControllerApi {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("api/users")
    public List<User> users() {
        return userService.listUsers();
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @PostMapping("api/users/register")
    public User registerUser(User user) {
        return userService.registerUser(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("api/users/delete")
    public void deleteUser(@RequestParam Long userId) {
        userService.deleteUser(userId);
    }
}
