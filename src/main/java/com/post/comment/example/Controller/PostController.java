package com.post.comment.example.Controller;

import com.post.comment.example.Model.Comment;
import com.post.comment.example.Model.Post;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
class PostController {
//    private final PostRepository repo;
    PostController(){}

    @GetMapping("/posts")
    public List<Post> getPosts(@RequestParam(value = "of", required = false) int userId){
        return null;
    }

    @GetMapping("/post")
    public Post getPostByRequestParam(@RequestParam(value = "id") int postId){
        return null;
    }

    @GetMapping("/post/{id}")
    public Post getPost(@PathVariable(value = "id", required = true) int postId){
        return null;
    }

    @GetMapping("/post/{id}/comments")
    public List<Comment> getComments(@PathVariable(value = "id") int postId){
        return null;
    }

    @GetMapping("/comments")
    public List<Comment> getCommentsByRequestParam(@RequestParam(value = "of") int postId){
        return null;
    }

    @PostMapping("/post")
    public Post sharePost(@RequestBody Post post){
        return null;
    }

    @PatchMapping("/post/{id}")
    public Post updatePost(@PathVariable(value = "id") int postId, @RequestBody Post post){
        return null;
    }

    @PatchMapping("/comment/{id}")
    public Post updateComment(@PathVariable(value= "id") int commentId, @RequestBody Post post){
        return null;
    }

}
