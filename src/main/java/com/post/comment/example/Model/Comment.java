package com.post.comment.example.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {
    @Id
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