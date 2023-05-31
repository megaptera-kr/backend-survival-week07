package kr.megaptera.assignment.applications.comments;

import kr.megaptera.assignment.models.comments.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeleteCommentServiceTest {
    private CommentRepository commentRepository;
    private DeleteCommentService deleteCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        deleteCommentService = new DeleteCommentService(commentRepository);
    }

    @Test
    @DisplayName("delete comment")
    void deleteComment() {
        var comment = new Comment("comment_id", "post_id", "author", "content");
        given(commentRepository.findById(comment.getId())).willReturn(Optional.of(comment));

        var postReadDto = deleteCommentService.execute(comment.getId());
        assertThat(postReadDto.getAuthor()).isEqualTo(comment.getAuthor());
        assertThat(postReadDto.getContent()).isEqualTo(comment.getContent());
    }
}