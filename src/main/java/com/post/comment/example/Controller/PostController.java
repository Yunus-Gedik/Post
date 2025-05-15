package com.post.comment.example.Controller;

import com.post.comment.example.Model.Post;
import com.post.comment.example.Model.PostDTO;
import com.post.comment.example.Model.User;
import com.post.comment.example.Repository.CommentRepository;
import com.post.comment.example.Repository.PostRepository;
import com.post.comment.example.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/post")
@RestController
class PostController {
    private final PostRepository postRepo;
    private final UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    PostController(PostRepository postRepo, UserRepository userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/all")
    public List<Post> getPosts(@RequestParam(value = "of", required = false) Long userId) {
        return postRepo.findByUserId(userId);
    }

    @GetMapping("")
    public Optional<Post> getPostByRequestParam(@RequestParam(value = "id") Long postId) {
        return postRepo.findById(postId);
    }

    @GetMapping("/{id}")
    public Optional<Post> getPost(@PathVariable(value = "id", required = true) long postId) {
        return postRepo.findById(postId);
    }

    @PostMapping("/new")
    public Post sharePost(@RequestBody PostDTO post) {
        Post p = new Post();
        mapDTOtoPost(post, p);
        return postRepo.save(p);
    }

    @PatchMapping("/{id}")
    public Post updatePost(@PathVariable(value = "id") Long postId, @RequestBody PostDTO post) {
        Post p = postRepo.findById(postId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found")
        );

        mapDTOtoPost(post, p);
        return postRepo.save(p);
    }

    private void mapDTOtoPost(PostDTO dto, Post p) {
        if (dto.title() != null) {
            p.setTitle(dto.title());
        }
        if (dto.body() != null) {
            p.setBody(dto.body());
        }
        if (dto.userId() != null) {
            User u = userRepo.findById(dto.userId()).orElseThrow();
            p.setUser(u);
        }
    }
}
