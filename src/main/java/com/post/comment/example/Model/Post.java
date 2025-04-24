package com.post.comment.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Primary;

@Entity
public class Post {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "userId", nullable = false)
    User user;

    @NotEmpty
    String title;
    @NotEmpty
    String body;
}