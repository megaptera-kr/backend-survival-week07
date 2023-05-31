package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCommentServiceTest {
    private CommentRepository commentRepository;
    private GetCommentService getCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);

        getCommentService = new GetCommentService(commentRepository);
    }

    @Test
    void list() {
        PostId postId = PostId.of("1");

        given(commentRepository.findAllByPostId(any())).willReturn(List.of(
            new Comment(CommentId.of("1"), postId, "작성자1", MultilineText.of("댓글 내용1")),
            new Comment(CommentId.of("2"), postId, "작성자2", MultilineText.of("댓글 내용2"))
        ));

        List<CommentDto> commentDtos = getCommentService.getCommentDtos(postId.toString());

        assertThat(commentDtos).hasSize(2);
    }
}
