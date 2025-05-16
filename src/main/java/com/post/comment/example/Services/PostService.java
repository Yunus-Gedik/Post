package com.post.comment.example.Services;

import com.post.comment.example.Model.Post;
import com.post.comment.example.Model.PostDTO;
import com.post.comment.example.Model.User;
import com.post.comment.example.Repository.PostRepository;
import com.post.comment.example.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepo;
    private final UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public PostService(PostRepository postRepo, UserRepository userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    public List<Post> getPostsByUserId(Long userId) {
        return postRepo.findByUserId(userId);
    }

    public Optional<Post> getPostById(Long postId) {
        return postRepo.findById(postId);
    }

    public Post createPost(PostDTO dto) {
        Post post = new Post();
        mapDTOtoPost(dto, post);
        return postRepo.save(post);
    }

    public Post updatePost(Long postId, PostDTO dto) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
        mapDTOtoPost(dto, post);
        return postRepo.save(post);
    }

    private void mapDTOtoPost(PostDTO dto, Post post) {
        if (dto.title() != null) {
            post.setTitle(dto.title());
        }
        if (dto.body() != null) {
            post.setBody(dto.body());
        }
        if (dto.userId() != null) {
            User user = userRepo.findById(dto.userId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
            post.setUser(user);
        }
    }
}
