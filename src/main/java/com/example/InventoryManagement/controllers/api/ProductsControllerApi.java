package com.example.InventoryManagement.controllers.api;

import com.example.InventoryManagement.entities.Product;
import com.example.InventoryManagement.services.CompanyService;
import com.example.InventoryManagement.services.ProductService;
import com.example.InventoryManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsControllerApi {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("api/products")
    public List<Product> products(Model model, @RequestParam(required = false) String search) {
        if (search != null) {
            return productService.searchProducts(search);
        } else {
            return productService.listProducts();
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @PostMapping("api/products/create")
    public Product createProduct(Product product, Long userID, Long companyID) {
        product.setCompany(companyService.findById(companyID));
        product.setUser(userService.findById(userID));
        productService.addProduct(product);
        return product;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("api/products/delete")
    public void deleteProduct(@RequestParam Long productId) {
        productService.deleteProduct(productId);
    }
}
