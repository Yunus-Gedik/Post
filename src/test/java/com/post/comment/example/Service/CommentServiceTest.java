package com.post.comment.example.Service;

import com.post.comment.example.Model.*;
import com.post.comment.example.Repository.CommentRepository;
import com.post.comment.example.Repository.PostRepository;
import com.post.comment.example.Services.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommentServiceTest {
    private CommentService commentService;
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    private User u1, u2;
    private Post p11, p21, p12, p22;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        postRepository = mock(PostRepository.class);
        commentService = new CommentService(commentRepository, postRepository);

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
    void testCreateComment() {
        Comment comment1 = new Comment(1L, "body1", "name1", "email1", p11);

        when(postRepository.findById(1L)).thenReturn(Optional.of(p11));

        ArgumentCaptor<Comment> commentCaptor = ArgumentCaptor.forClass(Comment.class);

        when(commentRepository.save(commentCaptor.capture())).thenAnswer(arg -> {
            Comment c = arg.getArgument(0);
            c.setId(1L);
            return c;
        });

        Comment result = commentService.createComment(comment1.toDTO());
        assertEquals(comment1.getBody(), result.getBody());
    }


}
