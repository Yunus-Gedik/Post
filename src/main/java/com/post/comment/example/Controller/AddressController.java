package com.post.comment.example.Controller;

import com.post.comment.example.Model.AddressDTO;
import com.post.comment.example.Repository.AddressRepository;
import com.post.comment.example.Services.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.post.comment.example.Model.Address;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService service;

    AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping("/new")
    public Address addNewAddress(@RequestBody(required = true) AddressDTO address) {
        return service.newAddress(address);
    }

    @GetMapping("/all")
    public List<Address> getAll(){
        return service.findAll();
    }
}
