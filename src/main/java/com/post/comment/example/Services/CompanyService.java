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
    private final ModelMapper mapper;

    public CompanyService(CompanyRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<Company> getAllCompanies() {
        return repo.findAll();
    }

    public Company createCompany(CompanyDTO company) {
        Company newCompany = mapper.map(company, Company.class);
        return repo.save(newCompany);
    }
}
