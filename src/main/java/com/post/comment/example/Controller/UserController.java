package com.post.comment.example.Controller;

import com.post.comment.example.Model.User;
import com.post.comment.example.Model.UserDTO;
import com.post.comment.example.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    UserController(UserRepository userRepository) {
        this.userRepo = userRepository;
    }

    @GetMapping("/all")
    public List<User> getUsers() {
        return this.userRepo.findAll();
    }

    @GetMapping("")
    public User getUser(@RequestParam(name = "id", required = true) Long userId) {
        return this.userRepo.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")
        );
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id", required = true) Long userId) {
        return this.userRepo.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")
        );
    }

    @PostMapping("/new")
    public User createUser(@RequestBody(required = true) UserDTO user) {
        User u = new User();

        this.modelMapper.map(user, u);
        this.userRepo.save(u);

        return u;
    }

    @PatchMapping("/edit/{id}")
    public User updateUser(
            @PathVariable(value = "id", required = true) Long userId,
            @RequestBody(required = true) UserDTO user
    ) {
        User u = this.userRepo.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")
        );

        this.modelMapper.map(user, u);
        this.userRepo.save(u);
        return u;
    }

}
