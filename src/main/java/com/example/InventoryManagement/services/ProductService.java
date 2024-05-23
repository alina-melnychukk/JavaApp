package com.example.InventoryManagement.services;

import com.example.InventoryManagement.entities.Product;
import com.example.InventoryManagement.entities.User;
import com.example.InventoryManagement.repositories.ProductRepository;
import com.example.InventoryManagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void addProduct(Product product) {
        repository.save(product);
    }

    public List<Product> listProducts() {
        return repository.findAll();
    }

    public void deleteProduct(Long product) {
       repository.deleteById(product);
    }

    public Product findById(Long productId) {
        return repository.findById(productId).orElse(null);
    }

    public List<Product> searchProducts(String searchTerm) {
        return  repository.findAll().stream()
                .filter(product -> product. getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                        product.getCategory().toLowerCase().contains(searchTerm.toLowerCase()))
                .toList();
    }
}
