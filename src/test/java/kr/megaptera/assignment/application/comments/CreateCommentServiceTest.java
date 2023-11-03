package kr.megaptera.assignment.application.comments;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.comments.CreateCommentDto;
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
class CreateCommentServiceTest {
    private CreateCommentService createCommentService;

    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        createCommentService = new CreateCommentService(postRepository);
    }

    @Test
    @DisplayName("createComment")
    void testCreateComment() {
        UUID mockPostId = UUID.randomUUID();

        Post mockPost = mock(Post.class);

        given(postRepository.findById(mockPostId)).willReturn(Optional.of(mockPost));

        String mockCommentAuthor = "author";
        String mockCommentContent = "content";

        CreateCommentDto createCommentDto = new CreateCommentDto(mockCommentAuthor, mockCommentContent);

        createCommentService.createComment(mockPostId.toString(), createCommentDto);

        verify(mockPost).addComment(mockCommentAuthor, mockCommentContent);
    }
}