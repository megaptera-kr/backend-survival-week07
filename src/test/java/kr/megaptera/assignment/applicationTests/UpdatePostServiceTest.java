package kr.megaptera.assignment.applicationTests;

import kr.megaptera.assignment.applications.*;
import kr.megaptera.assignment.domains.*;
import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

public class UpdatePostServiceTest {
    private UpdatePostService updatePostService;
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        updatePostService = new UpdatePostService(postRepository);
    }

    @Test
    @DisplayName("update")
    void update() {
        PostId postId = new PostId("001");

        Post post = new Post(postId, "title", "author", "content");

        given(postRepository.findById(postId)).willReturn(Optional.of(post));

        PostUpdateDto postUpdateDto = new PostUpdateDto("new title", "new content");
        updatePostService.updatePost(postId.toString(), postUpdateDto);

        assertThat(post.getTitle()).isEqualTo("new title");

    }
}
