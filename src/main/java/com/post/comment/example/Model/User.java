package com.post.comment.example.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class User {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String username;
    String email;

    @ManyToOne(optional = true)
    @JoinColumn(nullable = true, name = "addressId")
    Address address;
    String phone;
    String website;

    @ManyToOne(optional = true)
    @JoinColumn(nullable = true, name = "companyId")
    Company company;
}