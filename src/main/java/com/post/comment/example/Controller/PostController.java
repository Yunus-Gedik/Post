package com.post.comment.example.Controller;

import com.post.comment.example.Model.Comment;
import com.post.comment.example.Model.CommentOptional;
import com.post.comment.example.Model.Post;
import com.post.comment.example.Model.PostOptional;
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
    private final CommentRepository commentRepo;

    @Autowired
    private ModelMapper modelMapper;

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
        return this.postRepo.save(post);
    }

    @PostMapping("/comment")
    public Comment shareComment(@RequestBody Comment comment) {
        return this.commentRepo.save(comment);
    }

    @PatchMapping("/post/{id}")
    public Post updatePost(@PathVariable(value = "id") Long postId, @RequestBody PostOptional post) {
        Post p = this.postRepo.findById(postId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found")
        );

        modelMapper.map(post, p);
        this.postRepo.save(p);
        return p;
    }

    @PatchMapping("/comment/{id}")
    public Comment updateComment(@PathVariable(value = "id") Long commentId, @RequestBody CommentOptional comment) {
        Comment c = this.commentRepo.findById(commentId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found")
        );

        modelMapper.map(comment, c);
        this.commentRepo.save(c);
        return null;
    }


}
