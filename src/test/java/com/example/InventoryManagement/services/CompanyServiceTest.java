package com.example.InventoryManagement.services;

import com.example.InventoryManagement.entities.Company;
import com.example.InventoryManagement.repositories.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {
    CompanyService service;
    CompanyRepository repository;

    @BeforeEach
    void setUp() {
        repository = mock(CompanyRepository.class);
        service = new CompanyService(repository);
    }

    @Test
    void registerCompany() {
        Company company = new Company(1L, "test", "test", "380123456789");

        service.registerCompany(company);

        verify(repository).save(company);
    }

    @Test
    void listCompanies() {
        List<Company> companies = List.of(new Company(1L, "test", "test", "380123456789"));
        when(repository.findAll()).thenReturn(companies);

        List<Company> result = service.listCompanies();

        assertEquals(companies, result);
    }

    @Test
    void deleteCompany() {
        Company company = new Company(1L, "test", "test", "380123456789");

        service.deleteCompany(company.getID());

        verify(repository).deleteById(company.getID());
    }

    @Test
    void findById() {
        Company company = new Company(1L, "test", "test", "380123456789");
        when(repository.findById(company.getID())).thenReturn(Optional.of(company));

        Company result = service.findById(company.getID());

        assertEquals(company, result);
    }
}