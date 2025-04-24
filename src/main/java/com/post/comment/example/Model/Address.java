package com.post.comment.example.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Address {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String street;
    String suite;
    String city;
    String zipcode;

    @ManyToOne(optional = true)
    @JoinColumn(nullable = true, name = "geoId")
    Geo geo;
}