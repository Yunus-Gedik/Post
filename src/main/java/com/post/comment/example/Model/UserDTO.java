package com.post.comment.example.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    String name;
    String username;
    String email;
    String phone;
    String website;
    Long addressId;
    Long companyId;
}
