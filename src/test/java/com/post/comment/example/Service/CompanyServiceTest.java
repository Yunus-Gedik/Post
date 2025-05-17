package com.post.comment.example.Service;

import com.post.comment.example.Helper.ModelMapperConfig;
import com.post.comment.example.Model.CompanyDTO;
import com.post.comment.example.Services.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.post.comment.example.Model.Company;
import com.post.comment.example.Repository.CompanyRepository;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CompanyServiceTest {

    private CompanyRepository companyRepository;
    private CompanyService service;

    @BeforeEach
    public void setUp() {
        companyRepository = mock(CompanyRepository.class);
        ModelMapper modelMapper = new ModelMapperConfig().modelMapper();
        service = new CompanyService(companyRepository, modelMapper);
    }

    @Test
    public void testGetAllCompanies() {
        Company c1 = new Company(1L, "Company A", "Catch Phrase A", "bs A");
        Company c2 = new Company(2L, "Company B", "Catch Phrase B", "bs B");

        when(companyRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<Company> result = service.getAllCompanies();
        assertEquals(2, result.size());
    }

    @Test
    public void testCreateCompany() {
        Company c = new Company(null, "Company A", "Catch Phrase A", "bs A");

        ArgumentCaptor<Company> captor = ArgumentCaptor.forClass(Company.class);

        when(companyRepository.save(captor.capture())).thenAnswer(val -> {
            Company saved = val.getArgument(0);
            saved.setId(1L);
            return saved;
        });

        Company result = service.createCompany(c.toDTO());
        assertEquals("Company A", result.getName());
    }

}
