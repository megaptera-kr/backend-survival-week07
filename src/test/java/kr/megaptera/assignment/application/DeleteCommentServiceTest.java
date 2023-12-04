package kr.megaptera.assignment.application;

import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Transactional
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
        Long commentId = 1L;

        Long postId = 2L;

        Comment comment = new Comment(commentId, postId, "작성자", MultilineText.of("댓글 내용"));

        given(commentRepository.findByIdAndPostId(commentId, postId)).willReturn(comment);

        deleteCommentService.deleteComment(
                commentId.toString(),
                postId.toString()
        );

        verify(commentRepository).delete(any(Comment.class));
    }
}
