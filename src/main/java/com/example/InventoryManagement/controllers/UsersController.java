package com.example.InventoryManagement.controllers;

import com.example.InventoryManagement.entities.User;
import com.example.InventoryManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("users")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public String users(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "userList";
    }

    @PostMapping("users/register")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public String registerUser(User user) {
        userService.registerUser(user);
        return "redirect:/users";
    }

    @PostMapping("users/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String deleteUser(@RequestParam Long userId) {
        userService.deleteUser(userId);
        return "redirect:/users";
    }
}
