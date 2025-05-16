package com.post.comment.example.Controller;

import com.post.comment.example.Model.Company;
import com.post.comment.example.Model.CompanyDTO;
import com.post.comment.example.Services.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService service;

    CompanyController(CompanyService service) {
        this.service = service;
    }

    @PostMapping("/new")
    public Company addNewAddress(@RequestBody(required = true) CompanyDTO company) {
        return service.createCompany(company);
    }

    @GetMapping("/all")
    public List<Company> getAllCompany() {
        return service.getAllCompanies();
    }
}
