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
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty
    String name;
    @NotEmpty
    String username;
    @NotEmpty
    String email;

    String phone;
    String website;

    @ManyToOne(optional = true)
    @JoinColumn(nullable = true, name = "addressId")
    Address address;

    @ManyToOne(optional = true)
    @JoinColumn(nullable = true, name = "companyId")
    Company company;

    public UserDTO toDTO() {
        return new UserDTO(name, username, email, phone, website, address.getId(), company.getId());
    }
}