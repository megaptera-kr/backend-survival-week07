package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateCommentServiceTest {
    private CommentRepository commentRepository;

    private CreateCommentService createCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        createCommentService = new CreateCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 생성")
    void create() {
        String postId = "001POST";

        CommentCreateDto newComment = new CommentCreateDto("작성자", "댓글 내용");

        createCommentService.createComment(postId, newComment);

        verify(commentRepository).save(any(Comment.class));
    }
}
