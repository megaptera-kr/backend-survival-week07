package kr.megaptera.assignment.applications.comments;

import kr.megaptera.assignment.dtos.comments.CommentUpdateDto;
import kr.megaptera.assignment.models.comments.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
    @DisplayName("update comment")
    void update(){
        var comment = new Comment(
                "update_comment_id",
                "update_post_id",
                "author",
                "content");

        given(commentRepository.findById(comment.getId())).willReturn(Optional.of(comment));

        var commentUpdateDto = new CommentUpdateDto("updated content");
        var commentReadDto = updateCommentService.execute(comment.getId(), comment.getPostId(), commentUpdateDto);

        assertThat(commentReadDto.getContent()).isEqualTo(commentUpdateDto.getContent());
    }
}