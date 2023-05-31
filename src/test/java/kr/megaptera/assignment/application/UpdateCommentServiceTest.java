package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.request.RqUpdateCommentDto;
import kr.megaptera.assignment.models.Author;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.Content;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;

class UpdateCommentServiceTest {

    private CommentRepository commentRepository;

    private UpdateCommentService updateCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);

        updateCommentService = new UpdateCommentService(commentRepository);
    }

    @Test
    void UpdateComment() {
        PostId postId = new PostId("test");
        CommentId commentId = new CommentId("test");
        Author author = new Author("author");
        Content content = new Content("content");

        Comment comment = new Comment(commentId, postId, author, content);

        given(commentRepository.findById(commentId))
                .willReturn(Optional.of(comment));

        RqUpdateCommentDto dto = new RqUpdateCommentDto();
        dto.setContent("update");

        ResponseEntity<CommentDto> updateDto = updateCommentService.updateComment(dto, postId.toString(), commentId.toString());

        assertThat(updateDto.getBody().getContent()).isEqualTo("update");
    }
}