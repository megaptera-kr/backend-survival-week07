package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentUpdateRequestDto;
import kr.megaptera.assignment.entities.CommentEntity;
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
    String commentId = "1";
    String postId = "2";

    CommentEntity comment = new CommentEntity(commentId, postId, "작성자", "댓글 내용");

    given(commentRepository.findById(commentId)).willReturn(Optional.ofNullable(comment));

    CommentUpdateRequestDto commentUpdateRequestDto
        = new CommentUpdateRequestDto("변경된 댓글 내용");

    updateCommentService.updateComment(
        commentId,
        postId,
        commentUpdateRequestDto
    );

    assertThat(comment.getContent()).isEqualTo("변경된 댓글 내용");
  }
}
