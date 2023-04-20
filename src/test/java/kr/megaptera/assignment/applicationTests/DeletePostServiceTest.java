package kr.megaptera.assignment.applicationTests;

import kr.megaptera.assignment.applications.*;
import kr.megaptera.assignment.domains.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import static org.mockito.Mockito.*;

public class DeletePostServiceTest {
    private DeletePostService deletePostService;
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        deletePostService = new DeletePostService(postRepository);
    }

    @DisplayName("지우기")
    @Test
    void delete() {
        PostId postId = new PostId("001");

        Post post = new Post(postId, "author", "title", "content");

        deletePostService.deletePost(postId.toString());

        verify(postRepository).deleteById(any(PostId.class));
    }
}
