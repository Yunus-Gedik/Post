package com.post.comment.example.Service;

import com.post.comment.example.Model.*;
import com.post.comment.example.Repository.PostRepository;
import com.post.comment.example.Repository.UserRepository;
import com.post.comment.example.Services.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostServiceTest {
    private PostService postService;
    private PostRepository postRepo;
    private UserRepository userRepo;

    private User u1, u2;
    private Post p11, p21, p12, p22;

    @BeforeEach
    public void setUp() {
        postRepo = mock(PostRepository.class);
        userRepo = mock(UserRepository.class);
        postService = new PostService(postRepo, userRepo);

        Address a1 = new Address(1L, "Street 1", "Suite 1", "City 1", "Zipcode 1", new Geo("lat1", "lng1"));
        Address a2 = new Address(2L, "Street 2", "Suite 2", "City 2", "Zipcode 2", new Geo("lat2", "lng2"));
        Company c1 = new Company(1L, "Company A", "Catch Phrase A", "bs A");
        Company c2 = new Company(2L, "Company B", "Catch Phrase B", "bs B");
        u1 = new User(1L, "Name 1", "Username 1", "Email 1", "Phone 1", "Website 1", a1, c1);
        u2 = new User(2L, "Name 2", "Username 2", "Email 2", "Phone 2", "Website 2", a2, c2);
        p11 = new Post(1L, u1, "Title 11", "Body 11");
        p12 = new Post(1L, u1, "Title 12", "Body 12");
        p21 = new Post(2L, u2, "Title 21", "Body 21");
        p22 = new Post(2L, u2, "Title 22", "Body 22");
    }

    @Test
    public void testGetPostsByUserId() {
        when(postRepo.findByUserId(1L)).thenReturn(Arrays.asList(p11, p12));
        List<Post> posts = postService.getPostsByUserId(1L);
        assertEquals(2, posts.size());
    }

    @Test
    public void testGetPostById() {
        when(postRepo.findById(1L)).thenReturn(Optional.of(p11));
        Optional<Post> post = postService.getPostById(1L);
        assertEquals(p11, post.get());
    }

    @Test
    public void testCreatePost() {
        when(userRepo.findById(1L)).thenReturn(Optional.of(u1));

        ArgumentCaptor<Post> captor = ArgumentCaptor.forClass(Post.class);

        when(postRepo.save(captor.capture())).thenAnswer(value -> {
            Post post = captor.getValue();
            post.setId(1L);
            return post;
        });

        Post result = postService.createPost(p11.toDTO());
        assertEquals(p11.getBody(), result.getBody());
        assertEquals(p11.getTitle(), result.getTitle());
        assertEquals(p11.getUser().getId(), result.getUser().getId());
    }

    @Test
    public void testUpdatePost() {
        when(userRepo.findById(1L)).thenReturn(Optional.of(u1));
        when(postRepo.findById(1L)).thenReturn(Optional.of(p11));

        ArgumentCaptor<Post> captor = ArgumentCaptor.forClass(Post.class);
        when(postRepo.save(captor.capture())).thenAnswer(value -> {
            Post post = captor.getValue();
            post.setId(1L);
            return post;
        });

        PostDTO updateData = new PostDTO();
        updateData.setTitle("Update Title");
        Post result = postService.updatePost(p11.getId(), updateData);
        assertEquals(updateData.getTitle(), result.getTitle());
    }
}
