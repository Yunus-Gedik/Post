package com.post.comment.example.Services;

import com.post.comment.example.Model.Address;
import com.post.comment.example.Model.AddressDTO;
import com.post.comment.example.Repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository repo;
    private final ModelMapper mapper;

    public AddressService(AddressRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Address newAddress(AddressDTO address) {
        Address newAddress = mapper.map(address, Address.class);
        return repo.save(newAddress);
    }

    public List<Address> findAll() {
        return repo.findAll();
    }
}
