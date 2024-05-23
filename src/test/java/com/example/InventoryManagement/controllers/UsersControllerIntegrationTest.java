package com.example.InventoryManagement.controllers;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class UsersControllerIntegrationTest {
    private final MockMvc mockMvc;

    @Autowired
    UsersControllerIntegrationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void getUsers_authorizedUser_shouldReturnPage() throws Exception {
        mockMvc .perform(get("/users").with(user("user").roles("USER")))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
    }

    @Test
    void getUsers_unauthorizedUser_shoudRedirectToLoginPage() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }
    @Test
    void getUsers_basicUser_shouldNotContainRegisterForm() throws Exception {
        mockMvc .perform(get("/users").with(user("user").roles("USER")))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(content().string(not(containsString("action=\"/users/register\""))));
    }

    @Test
    void getUsers_admin_shouldContainRegisterForm() throws Exception {
        mockMvc .perform(get("/users").with(user("admin").roles("USER", "MANAGER", "ADMIN")))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(content().string(containsString("action=\"/users/register\"")));
    }
}