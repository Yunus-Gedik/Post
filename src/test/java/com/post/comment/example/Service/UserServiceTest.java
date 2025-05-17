package com.post.comment.example.Service;

import com.post.comment.example.Model.*;
import com.post.comment.example.Repository.AddressRepository;
import com.post.comment.example.Repository.CompanyRepository;
import com.post.comment.example.Repository.UserRepository;
import com.post.comment.example.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private CompanyRepository companyRepository;

    private Address a1;
    private Address a2;
    private Company c1;
    private Company c2;
    private User u1;
    private User u2;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        addressRepository = mock(AddressRepository.class);
        companyRepository = mock(CompanyRepository.class);
        userService = new UserService(userRepository, addressRepository, companyRepository);

        a1 = new Address(1L, "Street 1", "Suite 1", "City 1", "Zipcode 1", new Geo("lat1", "lng1"));
        a2 = new Address(2L, "Street 2", "Suite 2", "City 2", "Zipcode 2", new Geo("lat2", "lng2"));
        c1 = new Company(1L, "Company A", "Catch Phrase A", "bs A");
        c2 = new Company(2L, "Company B", "Catch Phrase B", "bs B");
        u1 = new User(1L, "Name 1", "Username 1", "Email 1", "Phone 1", "Website 1", a1, c1);
        u2 = new User(2L, "Name 2", "Username 2", "Email 2", "Phone 2", "Website 2", a2, c2);
    }

    @Test
    public void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(u1, u2));

        List<User> result = userService.getAllUsers();
        assertEquals(2, result.size());
        assertEquals(u1, result.get(0));
        assertEquals(u2, result.get(1));
        assertEquals(c1, result.get(0).getCompany());
        assertEquals(c2, result.get(1).getCompany());
    }

    @Test
    public void testGetUserByIdSuccess() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(u1));
        User result = userService.getUserById(1L);
        assertEquals(u1.getEmail(), result.getEmail());
    }

    @Test
    public void testGetUserByIdFail() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> userService.getUserById(1L));
    }

    @Test
    public void testCreateUser() {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        when(userRepository.save(captor.capture())).thenAnswer(value -> {
            User user = value.getArgument(0);
            user.setId(1L);
            return user;
        });

        when(companyRepository.findById(1L)).thenReturn(Optional.of(c1));
        when(addressRepository.findById(1L)).thenReturn(Optional.of(a1));

        User result = userService.createUser(u1.toDTO());
        assertEquals(u1.getPhone(), result.getPhone());
        assertEquals(u1.getCompany(), result.getCompany());
    }

    @Test
    public void testUpdateUserSuccess() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(c1));
        when(addressRepository.findById(1L)).thenReturn(Optional.of(a1));
        when(userRepository.findById(1L)).thenReturn(Optional.of(u1));

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        when(userRepository.save(captor.capture())).thenAnswer(value -> {
            return value.getArgument(0);
        });

        UserDTO updateData = new UserDTO();
        updateData.setName("Updated Name");

        User result = userService.updateUser(1L, updateData);
        assertEquals(updateData.getName(), result.getName());
    }

    @Test
    public void testUpdateUserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> userService.updateUser(3L, null));
    }
    
}
