package com.example.InventoryManagement.services;

import com.example.InventoryManagement.entities.Company;
import com.example.InventoryManagement.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public Company registerCompany(Company company) {
        repository.save(company);
        return company;
    }

    public List<Company> listCompanies() {
        return repository.findAll();
    }

    public void deleteCompany(Long companyId) {
       repository.deleteById(companyId);
    }

    public Company findById(Long companyId) {
        return repository.findById(companyId).orElse(null);
    }
}
