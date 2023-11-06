package kr.megaptera.assignment.application.comments;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.comments.UpdateCommentDto;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.models.columns.Author;
import kr.megaptera.assignment.models.columns.Content;
import kr.megaptera.assignment.models.comments.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Transactional
class UpdateCommentServiceTest {
    private UpdateCommentService updateCommentService;

    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        updateCommentService = new UpdateCommentService(commentRepository);
    }

    @Test
    @DisplayName("id로 comment를 찾은 경우 댓글 수정")
    void testUpdateCommentIfExists() {
        UUID mockCommentId = UUID.randomUUID();
        UUID mockPostId = UUID.randomUUID();
        Author mockCommentAuthor = new Author("author");
        Content mockCommentContent = new Content("content");

        Content mockCommentNewContent = new Content("content2");

        Comment comment = new Comment(
                mockCommentId,
                mockPostId,
                mockCommentAuthor,
                mockCommentContent
        );

        given(commentRepository.findById(mockCommentId)).willReturn(Optional.of(comment));

        UpdateCommentDto updateCommentDto = new UpdateCommentDto(mockCommentNewContent.toString());

        this.updateCommentService.updateComment(mockCommentId.toString(), updateCommentDto);

        verify(commentRepository).findById(mockCommentId);

        assertThat(comment.getContent()).isNotEqualTo(mockCommentContent);
        assertThat(comment.getContent()).isEqualTo(mockCommentNewContent);
    }

    @Test
    @DisplayName("id로 comment를 찾지 못한 경우 CommentNotFound 에러 발생")
    void testUpdateCommentIfNotExists() {
        UUID mockCommentId = UUID.randomUUID();

        String mockCommentNewContent = "content2";

        given(commentRepository.findById(mockCommentId)).willReturn(Optional.empty());

        UpdateCommentDto updateCommentDto = new UpdateCommentDto(mockCommentNewContent);

        assertThatThrownBy(
                () -> this.updateCommentService.updateComment(mockCommentId.toString(), updateCommentDto)
        ).isInstanceOf(CommentNotFound.class);
    }
}