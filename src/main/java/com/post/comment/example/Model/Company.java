package com.post.comment.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Company {
    @Id
    Long id;
    String name;
    String catchPhrase;
    String bs;
}