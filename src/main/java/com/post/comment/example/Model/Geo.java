package com.post.comment.example.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Geo {
    String lat;
    String lng;

    public GeoDTO toDTO(){
        return new GeoDTO(lat, lng);
    }
}