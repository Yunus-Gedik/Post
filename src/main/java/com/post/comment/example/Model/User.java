package com.post.comment.example.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
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