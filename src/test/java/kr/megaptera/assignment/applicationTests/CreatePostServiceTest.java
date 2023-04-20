package kr.megaptera.assignment.applicationTests;

import kr.megaptera.assignment.applications.*;
import kr.megaptera.assignment.domains.*;
import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class CreatePostServiceTest {

    private CreatePostService createPostService;
    private PostRepository postRepository;

    @BeforeEach
    void setup() {
        postRepository = mock(PostRepository.class);

        createPostService = new CreatePostService(postRepository);
    }

    @Test
    @DisplayName("Create Post")
    void creation() {
        createPostService.createPost(new PostCreateDto("title", "author", "content"));

        verify(postRepository).save(any(Post.class));
    }
}
