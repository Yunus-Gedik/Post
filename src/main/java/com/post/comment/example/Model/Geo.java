package com.post.comment.example.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter
@Getter
public class Geo {
    String lat;
    String lng;
}