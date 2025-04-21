package com.post.comment.example.Model;

public record Comment (
        Integer postId,
        Integer id,
        String body,
        String name,
        String email
){}