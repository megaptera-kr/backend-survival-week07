package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.request.RqCreatePostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreatePostServiceTest {

    private PostRepository postRepository;

    private CreatePostService createPostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);

        createPostService = new CreatePostService(postRepository);
    }

    @Test
    void createPost() {
        RqCreatePostDto dto = new RqCreatePostDto();
        dto.setAuthor("author");
        dto.setContent("content");
        dto.setTitle("title");

        createPostService.createPost(dto);

        verify(postRepository).save(any(Post.class));
    }
}