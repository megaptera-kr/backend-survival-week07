package kr.megaptera.assignment.applicationTests;

import kr.megaptera.assignment.applications.*;
import kr.megaptera.assignment.domains.*;
import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

public class GetPostServiceTest {
    private GetPostService getPostService;
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        getPostService = new GetPostService(postRepository);
    }

    @DisplayName("Get One post")
    @Test
    void get() {
        PostId postId = new PostId("001");
        given(postRepository.findById(postId.toString()))
                .willReturn(Optional.of(new Post(postId, "title", "author", "content")));

        PostDto postDto = new PostDto(postId.toString(), "title", "author", "content");
        assertThat(postDto.getTitle()).isEqualTo("author");

    }

}
