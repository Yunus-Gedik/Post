package com.post.comment.example.Model;

public record CommentDTO (
    String body,
    String name,
    String email,
    Long postId
){}
