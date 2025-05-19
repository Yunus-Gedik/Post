package com.post.comment.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "userId", nullable = false)
    User user;

    @NotEmpty
    String title;
    String body;

    public PostDTO toDTO(){
        return new PostDTO(user.id, title, body);
    }
}