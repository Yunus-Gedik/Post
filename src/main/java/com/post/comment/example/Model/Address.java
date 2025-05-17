package com.post.comment.example.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String street;
    String suite;
    String city;
    String zipcode;

    @Embedded
    Geo geo;

    public AddressDTO toDTO(){
        return new AddressDTO(street, suite, city, zipcode, geo.toDTO());
    }
}