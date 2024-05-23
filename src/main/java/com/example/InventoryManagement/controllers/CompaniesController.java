package com.example.InventoryManagement.controllers;

import com.example.InventoryManagement.entities.Company;
import com.example.InventoryManagement.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CompaniesController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("companies")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public String companies(Model model) {
        model.addAttribute("companies", companyService.listCompanies());
        return "companyList";
    }

    @PostMapping("companies/register")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public String registerCompany(Company company) {
        companyService.registerCompany(company);
        return "redirect:/companies";
    }

    @PostMapping("companies/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String deleteCompany(@RequestParam Long companyId) {
        companyService.deleteCompany(companyId);
        return "redirect:/companies";
    }
}
