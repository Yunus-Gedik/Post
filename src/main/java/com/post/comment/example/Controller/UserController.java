package com.post.comment.example.Controller;

import com.post.comment.example.Model.User;
import com.post.comment.example.Model.UserDTO;
import com.post.comment.example.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("")
    public User getUser(@RequestParam(name = "id") Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id", required = true) Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/new")
    public User createUser(@RequestBody UserDTO user) {
        return userService.createUser(user);
    }

    @PatchMapping("/edit/{id}")
    public User updateUser(@PathVariable(value = "id", required = true) Long userId, @RequestBody UserDTO user) {
        return userService.updateUser(userId, user);
    }
}
