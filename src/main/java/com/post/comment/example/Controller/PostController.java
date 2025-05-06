package com.post.comment.example.Controller;

import com.post.comment.example.Model.Post;
import com.post.comment.example.Model.PostDTO;
import com.post.comment.example.Repository.CommentRepository;
import com.post.comment.example.Repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
class PostController {
    private final PostRepository postRepo;

    @Autowired
    private ModelMapper modelMapper;

    PostController(PostRepository repo) {
        postRepo = repo;
    }

    @GetMapping("/posts")
    public List<Post> getPosts(@RequestParam(value = "of", required = false) Long userId) {
        return postRepo.findByUserId(userId);
    }

    @GetMapping("/post")
    public Optional<Post> getPostByRequestParam(@RequestParam(value = "id") Long postId) {
        return postRepo.findById(postId);
    }

    @GetMapping("/post/{id}")
    public Optional<Post> getPost(@PathVariable(value = "id", required = true) long postId) {
        return postRepo.findById(postId);
    }

    @PostMapping("/post")
    public Post sharePost(@RequestBody PostDTO post) {
        Post p = new Post();
        modelMapper.map(post, p);
        return postRepo.save(p);
    }

    @PatchMapping("/post/{id}")
    public Post updatePost(@PathVariable(value = "id") Long postId, @RequestBody PostDTO post) {
        Post p = postRepo.findById(postId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found")
        );

        modelMapper.map(post, p);
        postRepo.save(p);
        return p;
    }
}
