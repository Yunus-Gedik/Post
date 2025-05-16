package com.post.comment.example.Controller;

import com.post.comment.example.Model.Post;
import com.post.comment.example.Model.PostDTO;
import com.post.comment.example.Services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/post")
@RestController
class PostController {
    private final PostService postService;

    PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/all")
    public List<Post> getPosts(@RequestParam(value = "of", required = false) Long userId) {
        return postService.getPostsByUserId(userId);
    }

    @GetMapping("")
    public Optional<Post> getPostByRequestParam(@RequestParam(value = "id") Long postId) {
        return postService.getPostById(postId);
    }

    @GetMapping("/{id}")
    public Optional<Post> getPost(@PathVariable(value = "id", required = true) long postId) {
        return postService.getPostById(postId);
    }

    @PostMapping("/new")
    public Post sharePost(@RequestBody PostDTO post) {
        return postService.createPost(post);
    }

    @PatchMapping("/{id}")
    public Post updatePost(@PathVariable(value = "id") Long postId, @RequestBody PostDTO post) {
        return postService.updatePost(postId, post);
    }
}
