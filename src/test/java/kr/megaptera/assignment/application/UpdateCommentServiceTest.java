package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentUpdateDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UpdateCommentServiceTest {
    CommentRepository commentRepository;
    UpdateCommentService updateCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        updateCommentService = new UpdateCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 수정")
    void update() {
        CommentId commentId = new CommentId("001COMMENT");

        PostId postId = new PostId("001POST");

        Comment comment = new Comment(commentId, postId, "작성자", "내용");

        given(commentRepository.findByIdAndPostId(commentId, postId)).willReturn(Optional.of(comment));

        CommentUpdateDto commentUpdateDto = new CommentUpdateDto("변경된 댓글 내용");

        updateCommentService.updateComment(commentId.toString(), postId.toString(), commentUpdateDto);

        assertThat(comment.content()).isEqualTo("변경된 댓글 내용");

    }
}
