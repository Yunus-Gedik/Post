package com.post.comment.example.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public CommentDTO toDTO(){
        return new CommentDTO(body, name, email, post.getId());
    }
}