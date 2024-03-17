package kr.megaptera.assignment.application;

import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
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
    void delete() {
        PostId postId = new PostId("001POST");
        CommentId commentId = new CommentId("COMMENT");
        Comment comment = new Comment(commentId, postId, "AUTHOR", "COMMENT_CONTENT");

        given(commentRepository.findByIdAndPostId(commentId, postId))
            .willReturn(Optional.of(comment));

        deleteCommentService.deleteComment(
                commentId.toString(),
                postId.toString()
        );

        verify(commentRepository).delete(any(Comment.class));
    }
}