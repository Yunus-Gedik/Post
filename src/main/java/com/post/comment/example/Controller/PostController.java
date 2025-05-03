package com.post.comment.example.Controller;

import com.post.comment.example.Model.Comment;
import com.post.comment.example.Model.Post;
import com.post.comment.example.Repository.CommentRepository;
import com.post.comment.example.Repository.PostRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
class PostController {
    private final PostRepository postRepo;
    private final CommentRepository commentRepo;
    PostController(PostRepository repo, CommentRepository commentRepo) {
        this.postRepo = repo;
        this.commentRepo = commentRepo;
    }

    @GetMapping("/posts")
    public List<Post> getPosts(@RequestParam(value = "of", required = false) Long userId) {
        return this.postRepo.findByUserId(userId);
    }

    @GetMapping("/post")
    public Optional<Post> getPostByRequestParam(@RequestParam(value = "id") Long postId) {
        return postRepo.findById(postId);
    }

    @GetMapping("/post/{id}")
    public Optional<Post> getPost(@PathVariable(value = "id", required = true) long postId) {
        return postRepo.findById(postId);
    }

    @GetMapping("/post/{id}/comments")
    public List<Comment> getComments(@PathVariable(value = "id") long postId) {
        return commentRepo.findByPostId(postId);
    }

    @GetMapping("/comments")
    public List<Comment> getCommentsByRequestParam(@RequestParam(value = "of", required = true) Long postId) {
        return commentRepo.findByPostId(postId);
    }


    @PostMapping("/post")
    public Post sharePost(@RequestBody Post post) {
        return null;
    }

    @PatchMapping("/post/{id}")
    public Post updatePost(@PathVariable(value = "id") int postId, @RequestBody Post post) {
        return null;
    }

    @PatchMapping("/comment/{id}")
    public Post updateComment(@PathVariable(value = "id") int commentId, @RequestBody Post post) {
        return null;
    }


}
