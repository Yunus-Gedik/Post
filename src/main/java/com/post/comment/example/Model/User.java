package com.post.comment.example.Model;

public record User (
    Integer id,
    String name,
    String username,
    String email,
    Address address,
    String phone,
    String website,
    Company company
){}