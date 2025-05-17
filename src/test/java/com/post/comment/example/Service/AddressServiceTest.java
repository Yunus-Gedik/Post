package com.post.comment.example.Service;

import com.post.comment.example.Helper.ModelMapperConfig;
import com.post.comment.example.Model.Address;
import com.post.comment.example.Model.Geo;
import com.post.comment.example.Repository.AddressRepository;
import com.post.comment.example.Services.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressServiceTest {
    private AddressService service;
    private AddressRepository repo;

    @BeforeEach
    public void setUp() {
        repo = mock(AddressRepository.class);
        service = new AddressService(repo, new ModelMapperConfig().modelMapper());
    }

    @Test
    public void testAddAddress() {
        Address address = new Address(1L, "Street 1", "Suite 1", "City 1", "Zipcode 1",
                                        new Geo("lat", "lng"));
        ArgumentCaptor<Address> captor = ArgumentCaptor.forClass(Address.class);

        when(repo.save(captor.capture())).thenAnswer(val -> {
            Address saved = val.getArgument(0);
            saved.setId(1L);
            return saved;
        });

        Address result = service.newAddress(address.toDTO());
        assertEquals("Street 1", result.getStreet());
    }

    @Test
    public void testGetAllAddresses() {
        Address a1 = new Address(1L, "Street 1", "Suite 1", "City 1", "Zipcode 1",
                new Geo("lat1", "lng1"));
        Address a2 = new Address(2L, "Street 2", "Suite 2", "City 2", "Zipcode 2",
                new Geo("lat2", "lng2"));

        when(repo.findAll()).thenAnswer(val -> Arrays.asList(a1, a2));

        List<Address> result = service.findAll();
        assertEquals(2, result.size());
        assertEquals(a1, result.get(0));
        assertEquals(a2, result.get(1));
    }
}
