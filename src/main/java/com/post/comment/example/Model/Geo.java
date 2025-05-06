package com.post.comment.example.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class Geo {
    String lat;
    String lng;
}