package com.post.comment.example.Service;

import com.post.comment.example.Model.Comment;
import com.post.comment.example.Model.CommentDTO;
import com.post.comment.example.Model.Post;
import com.post.comment.example.Repository.CommentRepository;
import com.post.comment.example.Repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepo;
    private final PostRepository postRepo;

    @Autowired
    private ModelMapper mapper;

    public CommentService(CommentRepository commentRepo, PostRepository postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepo.findByPostId(postId);
    }

    public Comment createComment(CommentDTO dto) {
        Comment comment = new Comment();
        mapDTOtoComment(dto, comment);
        return commentRepo.save(comment);
    }

    public Comment updateComment(Long id, CommentDTO dto) {
        Comment comment = commentRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));
        mapDTOtoComment(dto, comment);
        return commentRepo.save(comment);
    }

    private void mapDTOtoComment(CommentDTO dto, Comment comment) {
        if (dto.name() != null) {
            comment.setName(dto.name());
        }
        if (dto.email() != null) {
            comment.setEmail(dto.email());
        }
        if (dto.body() != null) {
            comment.setBody(dto.body());
        }
        if (dto.postId() != null) {
            Post post = postRepo.findById(dto.postId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
            comment.setPost(post);
        }
    }
}