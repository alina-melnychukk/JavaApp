package com.example.InventoryManagement.controllers.api;

import com.example.InventoryManagement.entities.Company;
import com.example.InventoryManagement.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompaniesControllerApi {
    @Autowired
    private CompanyService companyService;

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("api/companies")
    public List<Company> companies() {
        return companyService.listCompanies();
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @PostMapping("api/companies/register")
    public Company registerCompany(Company company) {
        return companyService.registerCompany(company);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("api/companies/delete")
    public void deleteCompany(Long companyId) {
        companyService.deleteCompany(companyId);
    }
}
