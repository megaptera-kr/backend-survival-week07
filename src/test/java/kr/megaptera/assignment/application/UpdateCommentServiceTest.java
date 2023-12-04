package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentUpdatedDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UpdateCommentServiceTest {
    CommentRepository commentDao;

    UpdateCommentService updateCommentService;

    @BeforeEach
    void setUp() {
        commentDao = mock(CommentRepository.class);

        updateCommentService = new UpdateCommentService(commentDao);
    }

    @Test
    @DisplayName("댓글 수정")
    void update() {
        Long commentId = 1L;
        Long postId = 2L;

        Comment comment = new Comment(commentId, postId, "작성자", MultilineText.of("댓글 내용"));

        given(commentDao.findByIdAndPostId(commentId, postId)).willReturn(comment);

        CommentUpdatedDto commentUpdatedDto
                = new CommentUpdatedDto("변경된 댓글 내용");

        updateCommentService.updateComment(
                commentId.toString(),
                postId.toString(),
                commentUpdatedDto
        );

        assertThat(comment.content().toString()).isEqualTo("변경된 댓글 내용");
    }
}
