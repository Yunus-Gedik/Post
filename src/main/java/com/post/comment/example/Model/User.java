package com.post.comment.example.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class User {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty
    String name;
    @NotEmpty
    String username;
    @NotEmpty
    String email;
    
    String phone;
    String website;

    @ManyToOne(optional = true)
    @JoinColumn(nullable = true, name = "addressId")
    Address address;

    @ManyToOne(optional = true)
    @JoinColumn(nullable = true, name = "companyId")
    Company company;
}