package kr.megaptera.assignment.application;

import kr.megaptera.assignment.entities.CommentEntity;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteCommentServiceTest {
  private CommentRepository commentRepository;

  private DeleteCommentService deleteCommentService;

  @BeforeEach
  void setUp() {
    commentRepository = mock(CommentRepository.class);

    deleteCommentService = new DeleteCommentService(commentRepository);
  }

  @Test
  @DisplayName("댓글 삭제")
  void delete() {
    String commentId = "1";
    String postId = "2";

    CommentEntity comment = new CommentEntity(commentId, postId, "작성자", "댓글 내용");

    given(commentRepository.findById(commentId)).willReturn(Optional.ofNullable(comment));

    deleteCommentService.deleteComment(
        commentId,
        postId
    );

    verify(commentRepository).deleteById(any(String.class));
  }

}
