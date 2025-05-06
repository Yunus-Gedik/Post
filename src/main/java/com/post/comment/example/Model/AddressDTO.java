package com.post.comment.example.Model;

public record AddressDTO(
        String street,
        String suite,
        String city,
        String zipcode,
        String lat,
        String lng
) { }
