package com.example.InventoryManagement.controllers;

import com.example.InventoryManagement.entities.Product;
import com.example.InventoryManagement.services.CompanyService;
import com.example.InventoryManagement.services.ProductService;
import com.example.InventoryManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductsController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = {"/","/products"})
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public String products(Model model, @RequestParam(required = false) String search) {
        if (search != null) {
            model.addAttribute("products", productService.searchProducts(search));
        } else {
            model.addAttribute("products", productService.listProducts());
        }
        model.addAttribute("availableUsers", userService.listUsers());
        model.addAttribute("availableCompanies", companyService.listCompanies());
        model.addAttribute("newProduct", new Product());
        return "productList";
    }

    @PostMapping("products/create")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public String createProduct(Product product, Long userID, Long companyID) {
        product.setCompany(companyService.findById(companyID));
        product.setUser(userService.findById(userID));
        productService.addProduct(product);
        return "redirect:/products";
    }

    @PostMapping("products/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String deleteProduct(@RequestParam Long productId) {
        productService.deleteProduct(productId);
        return "redirect:/products";
    }
}
