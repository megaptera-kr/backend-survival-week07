package kr.megaptera.assignment.application.comments;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.models.posts.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Transactional
class GetCommentsServiceTest {
    private GetCommentsService getCommentsService;

    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        getCommentsService = new GetCommentsService(postRepository);
    }

    @Test
    @DisplayName("getComments")
    void testGetComments() {
        UUID mockPostId = UUID.randomUUID();

        Post mockPost = mock(Post.class);

        given(postRepository.findById(mockPostId)).willReturn(Optional.of(mockPost));

        getCommentsService.getComments(mockPostId.toString());

        verify(mockPost).findAllComments();
    }
}