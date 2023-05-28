package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentUpdateDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UpdateCommentServiceTest {
    private CommentRepository commentRepository;

    private UpdateCommentService updateCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);

        updateCommentService = new UpdateCommentService(commentRepository);
    }

    @Test
    void update() {
        String commentId = "1";
        String postId = "1";

        Comment comment = new Comment(CommentId.of(commentId), PostId.of(postId), "작성자", MultilineText.of("내용"));
        given(commentRepository.findByIdAndPostId(any(), any())).willReturn(
            Optional.of(comment));

        CommentUpdateDto commentUpdateDto = new CommentUpdateDto("수정 댓글 내용");

        updateCommentService.update(commentId, postId, commentUpdateDto);

        assertThat(comment.content().toString()).isEqualTo("수정 댓글 내용");
    }
}
