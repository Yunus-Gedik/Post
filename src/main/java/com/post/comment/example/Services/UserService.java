package com.post.comment.example.Services;

import com.post.comment.example.Model.User;
import com.post.comment.example.Model.UserDTO;
import com.post.comment.example.Model.Address;
import com.post.comment.example.Model.Company;
import com.post.comment.example.Repository.UserRepository;
import com.post.comment.example.Repository.AddressRepository;
import com.post.comment.example.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final AddressRepository addressRepo;
    private final CompanyRepository companyRepo;

    @Autowired
    public UserService(UserRepository userRepo, AddressRepository addressRepo, CompanyRepository companyRepo) {
        this.userRepo = userRepo;
        this.addressRepo = addressRepo;
        this.companyRepo = companyRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public User createUser(UserDTO dto) {
        User user = new User();
        mapDTOtoUser(dto, user);
        return userRepo.save(user);
    }

    public User updateUser(Long userId, UserDTO dto) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        mapDTOtoUser(dto, user);
        return userRepo.save(user);
    }

    private void mapDTOtoUser(UserDTO dto, User user) {
        if (dto.getName() != null) {
            user.setName(dto.getName());
        }
        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getPhone() != null) {
            user.setPhone(dto.getPhone());
        }
        if (dto.getWebsite() != null) {
            user.setWebsite(dto.getWebsite());
        }
        if (dto.getAddressId() != null) {
            Address address = addressRepo.findById(dto.getAddressId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found"));
            user.setAddress(address);
        }
        if (dto.getCompanyId() != null) {
            Company company = companyRepo.findById(dto.getCompanyId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));
            user.setCompany(company);
        }
    }
}
