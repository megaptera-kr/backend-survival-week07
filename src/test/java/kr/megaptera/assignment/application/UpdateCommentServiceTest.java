package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentUpdateDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UpdateCommentServiceTest {

    private CommentRepository commentRepository;

    UpdateCommentService updateCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);

        updateCommentService = new UpdateCommentService(commentRepository);
    }

    @Test
    void update() {
        PostId postId = new PostId("001POST");
        CommentId commentId = new CommentId("COMMENT");
        Comment comment = new Comment(commentId, postId, "AUTHOR", "CONTENT");

        given(commentRepository.findByIdAndPostId(commentId, postId))
                .willReturn(Optional.of(comment));

        CommentUpdateDto commentUpdateDto = new CommentUpdateDto("UPDATED_COMMENT_CONTENT");

        updateCommentService.updateComment(
                commentId.toString(),
                postId.toString(),
                commentUpdateDto
        );

        assertThat(comment.content()).isEqualTo("UPDATED_COMMENT_CONTENT");
    }
}