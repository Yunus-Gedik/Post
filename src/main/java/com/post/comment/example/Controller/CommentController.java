package com.post.comment.example.Controller;

import com.post.comment.example.Model.Comment;
import com.post.comment.example.Model.CommentDTO;
import com.post.comment.example.Repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController{
    private final CommentRepository commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    CommentController(CommentRepository commentRepo) {
        this.commentRepo = commentRepo;
    }

    @GetMapping("/post/{id}")
    public List<Comment> getComments(@PathVariable(value = "id") long postId) {
        return commentRepo.findByPostId(postId);
    }

    @GetMapping("")
    public List<Comment> getCommentsByRequestParam(@RequestParam(value = "of", required = true) Long postId) {
        return commentRepo.findByPostId(postId);
    }

    @PostMapping("/new")
    public Comment shareComment(@RequestBody CommentDTO comment) {
        Comment c = new Comment();
        modelMapper.map(comment, c);
        return commentRepo.save(c);
    }

    @PatchMapping("/{id}")
    public Comment updateComment(@PathVariable(value = "id") Long commentId, @RequestBody CommentDTO comment) {
        Comment c = commentRepo.findById(commentId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found")
        );

        modelMapper.map(comment, c);
        commentRepo.save(c);
        return null;
    }
}
