package com.post.comment.example.Services;

import com.post.comment.example.Model.Company;
import com.post.comment.example.Model.CompanyDTO;
import com.post.comment.example.Repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository repo;

    @Autowired
    ModelMapper mapper;

    @Autowired
    public CompanyService(CompanyRepository repo) {
        this.repo = repo;
    }

    public List<Company> getAllCompanies() {
        return repo.findAll();
    }

    public Company createCompany(CompanyDTO company) {
        Company newCompany = mapper.map(company, Company.class);
        return repo.save(newCompany);
    }
}
