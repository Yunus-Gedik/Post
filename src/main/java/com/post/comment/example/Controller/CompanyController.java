package com.post.comment.example.Controller;

import com.post.comment.example.Model.AddressDTO;
import com.post.comment.example.Model.Company;
import com.post.comment.example.Model.CompanyDTO;
import com.post.comment.example.Repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyRepository repo;

    @Autowired
    ModelMapper mapper;

    CompanyController(CompanyRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/new")
    public Company addNewAddress(@RequestParam(required = true) CompanyDTO company) {
        Company newCompany = new Company();
        mapper.map(company, newCompany);
        return repo.save(newCompany);
    }

    @GetMapping("/all")
    public List<Company> getAllCompany() {
        return repo.findAll();
    }
}
