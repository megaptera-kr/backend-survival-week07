package kr.megaptera.assignment.applicationTests;

import kr.megaptera.assignment.applications.*;
import kr.megaptera.assignment.domains.*;
import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

public class UpdateCommentsServiceTest {
    private UpdateCommentService updateCommentService;
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        updateCommentService = new UpdateCommentService(commentRepository);
    }

    @Test
    @DisplayName("update!")
    void update() {
        CommentId commentId = new CommentId("0001");
        PostId postId = new PostId("001");

        Comment comment = new Comment(commentId, "author", "content", postId);
        given(commentRepository.findByIdAndPostId(commentId.toString(), postId.toString()))
                .willReturn(Optional.of(comment));

        CommentUpdateDto commentUpdateDto = new CommentUpdateDto("new content");
        updateCommentService.updateCommentDto(commentId.toString(), postId.toString(), commentUpdateDto);
        assertThat(comment.getContent()).isEqualTo("new content");
    }
}
