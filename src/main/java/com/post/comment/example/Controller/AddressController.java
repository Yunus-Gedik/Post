package com.post.comment.example.Controller;

import com.post.comment.example.Model.AddressDTO;
import com.post.comment.example.Repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.post.comment.example.Model.Address;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressRepository repo;

    @Autowired
    ModelMapper modelMapper;

    AddressController(AddressRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/new")
    public Address addNewAddress(@RequestParam(required = true) AddressDTO address) {
        Address newAddress = new Address();
        modelMapper.map(address, newAddress);
        return repo.save(newAddress);
    }

    @GetMapping("/all")
    public List<Address> getAll(){
        return repo.findAll();
    }

}
