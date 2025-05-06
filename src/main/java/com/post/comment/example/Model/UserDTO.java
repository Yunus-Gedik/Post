package com.post.comment.example.Model;

public record UserDTO (
    String name,
    String username,
    String email,
    String phone,
    String website,
    Long addressId,
    Long companyId
){}
