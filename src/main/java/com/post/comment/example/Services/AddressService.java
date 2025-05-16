package com.post.comment.example.Services;

import com.post.comment.example.Model.Address;
import com.post.comment.example.Model.AddressDTO;
import com.post.comment.example.Repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository repo;

    @Autowired
    ModelMapper modelMapper;

    public AddressService(AddressRepository repo) {
        this.repo = repo;
    }

    public Address newAddress(AddressDTO address) {
        Address newAddress = modelMapper.map(address, Address.class);
        return repo.save(newAddress);
    }

    public List<Address> findAll() {
        return repo.findAll();
    }
}
