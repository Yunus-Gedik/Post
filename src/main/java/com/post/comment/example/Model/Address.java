package com.post.comment.example.Model;

public record Address (
    String street,
    String suite,
    String city,
    String zipcode,
    Geo geo
){}