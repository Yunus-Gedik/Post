package com.post.comment.example.Controller;

import com.post.comment.example.Model.Comment;
import com.post.comment.example.Model.CommentDTO;
import com.post.comment.example.Model.Post;
import com.post.comment.example.Repository.CommentRepository;
import com.post.comment.example.Repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentRepository commentRepo;
    private final PostRepository postRepo;

    @Autowired
    private ModelMapper modelMapper;

    CommentController(CommentRepository commentRepo, PostRepository postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
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
        mapDTOtoComment(comment, c);
        return commentRepo.save(c);
    }

    @PatchMapping("/{id}")
    public Comment updateComment(@PathVariable(value = "id") Long commentId, @RequestBody CommentDTO comment) {
        Comment c = commentRepo.findById(commentId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found")
        );

        mapDTOtoComment(comment, c);
        return commentRepo.save(c);
    }

    private void mapDTOtoComment(CommentDTO dto, Comment c) {
        if (dto.name() != null) {
            c.setName(dto.name());
        }
        if (dto.email() != null) {
            c.setEmail(dto.email());
        }
        if (dto.body() != null) {
            c.setBody(dto.body());
        }
        if (dto.postId() != null) {
            Post p = postRepo.findById(dto.postId()).orElseThrow();
            c.setPost(p);
        }
    }
}
