package com.example.InventoryManagement.services;

import com.example.InventoryManagement.entities.Product;
import com.example.InventoryManagement.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    ProductService service;
    ProductRepository repository;

    @BeforeEach
    void setUp() {
        repository = mock(ProductRepository.class);
        service = new ProductService(repository);
    }

    @Test
    void addProduct() {
        Product product = new Product(1L, "test", "test", true, null, null);

        service.addProduct(product);

        verify(repository).save(product);
    }

    @Test
    void listProducts() {
        List<Product> products = List.of(new Product(1L, "test", "test", true, null, null));
        when(repository.findAll()).thenReturn(products);

        List<Product> result = service.listProducts();

        assertEquals(products, result);
    }

    @Test
    void deleteProduct() {
        Product product = new Product(1L, "test", "test", true, null, null);

        service.deleteProduct(product.getID());

        verify(repository).deleteById(product.getID());
    }

    @Test
    void findById() {
        Product product = new Product(1L, "test", "test", true, null, null);
        when(repository.findById(product.getID())).thenReturn(Optional.of(product));

        Product result = service.findById(product.getID());

        assertEquals(product, result);
    }

    @Test
    void searchProducts() {
        List<Product> products = List.of(
                new Product(1L, "device", "keyboard", true, null, null),
                new Product(2L, "device", "IPhone 5", true, null, null),
                new Product(3L, "sport equipment", "snowboard", true, null, null),
                new Product(4L, "board game", "monopoly", true, null, null)
        );
        when(repository.findAll()).thenReturn(products);

        List<Product> result = service.searchProducts("board");

        assertTrue(result.contains(products.get(0)));
        assertTrue(result.contains(products.get(2)));
        assertTrue(result.contains(products.get(3)));
    }
}