package com.post.comment.example.Controller;

import com.post.comment.example.Model.Comment;
import com.post.comment.example.Model.CommentDTO;
import com.post.comment.example.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/post/{id}")
    public List<Comment> getComments(@PathVariable(value = "id") long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("")
    public List<Comment> getCommentsByRequestParam(@RequestParam(value = "of") Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @PostMapping("/new")
    public Comment shareComment(@RequestBody CommentDTO comment) {
        return commentService.createComment(comment);
    }

    @PatchMapping("/{id}")
    public Comment updateComment(@PathVariable(value = "id") Long commentId, @RequestBody CommentDTO comment) {
        return commentService.updateComment(commentId, comment);
    }
}
