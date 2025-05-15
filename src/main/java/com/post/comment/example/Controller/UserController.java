package com.post.comment.example.Controller;

import com.post.comment.example.Model.Address;
import com.post.comment.example.Model.Company;
import com.post.comment.example.Model.User;
import com.post.comment.example.Model.UserDTO;
import com.post.comment.example.Repository.AddressRepository;
import com.post.comment.example.Repository.CompanyRepository;
import com.post.comment.example.Repository.PostRepository;
import com.post.comment.example.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserRepository userRepo;
    private final AddressRepository addressRepo;
    private final CompanyRepository companyRepo;

    @Autowired
    private ModelMapper modelMapper;

    UserController(UserRepository userRepository, AddressRepository addressRepository, CompanyRepository companyRepository) {
        this.userRepo = userRepository;
        this.addressRepo = addressRepository;
        this.companyRepo = companyRepository;
    }

    @GetMapping("/all")
    public List<User> getUsers() {
        return this.userRepo.findAll();
    }

    @GetMapping("")
    public User getUser(@RequestParam(name = "id", required = true) Long userId) {
        return this.userRepo.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")
        );
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id", required = true) Long userId) {
        return this.userRepo.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")
        );
    }

    @PostMapping("/new")
    public User createUser(@RequestBody(required = true) UserDTO user) {
        User u = new User();
        mapDTOtoUser(user, u);
        return userRepo.save(u);
    }

    @PatchMapping("/edit/{id}")
    public User updateUser(
            @PathVariable(value = "id", required = true) Long userId,
            @RequestBody(required = true) UserDTO user
    ) {
        User u = this.userRepo.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")
        );

        mapDTOtoUser(user, u);
        return userRepo.save(u);
    }

    private void mapDTOtoUser(UserDTO userDTO, User u) {
        if (userDTO.name() != null) {
            u.setName(userDTO.name());
        }
        if (userDTO.username() != null) {
            u.setUsername(userDTO.username());
        }
        if (userDTO.email() != null) {
            u.setEmail(userDTO.email());
        }
        if (userDTO.phone() != null) {
            u.setPhone(userDTO.phone());
        }
        if (userDTO.website() != null) {
            u.setWebsite(userDTO.website());
        }
        if (userDTO.addressId() != null) {
            Address address = addressRepo.findById(userDTO.addressId()).orElseThrow();
            u.setAddress(address);
        }
        if (userDTO.companyId() != null) {
            Company company = companyRepo.findById(userDTO.companyId()).orElseThrow();
            u.setCompany(company);
        }
    }
}
