package com.post.comment.example.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Comment {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty
    String body;
    String name;
    String email;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "postId")
    Post post;
}