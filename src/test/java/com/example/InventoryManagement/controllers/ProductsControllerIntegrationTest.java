package com.example.InventoryManagement.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ProductsControllerIntegrationTest {
    private final MockMvc mockMvc;

    @Autowired
    ProductsControllerIntegrationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void getProducts_authorizedUser_shouldReturnPage() throws Exception {
        mockMvc .perform(get("/products").with(user("user").roles("USER")))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
    }

    @Test
    void getProducts_unauthorizedUser_shoudRedirectToLoginPage() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }
    @Test
    void getProducts_basicUser_shouldNotContainCreateForm() throws Exception {
        mockMvc .perform(get("/products").with(user("user").roles("USER")))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(content().string(not(containsString("action=\"/products/create\""))));
    }

    @Test
    void getProducts_admin_shouldContainCreateForm() throws Exception {
        mockMvc .perform(get("/products").with(user("admin").roles("USER", "MANAGER", "ADMIN")))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(content().string(containsString("action=\"/products/create\"")));
    }
}