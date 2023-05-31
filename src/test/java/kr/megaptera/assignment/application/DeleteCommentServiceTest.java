package kr.megaptera.assignment.application;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.domains.dto.CommentDto;
import kr.megaptera.assignment.domains.model.Comment;
import kr.megaptera.assignment.domains.model.CommentAuthor;
import kr.megaptera.assignment.domains.model.CommentId;
import kr.megaptera.assignment.domains.model.MultilineText;
import kr.megaptera.assignment.domains.model.Post;
import kr.megaptera.assignment.domains.model.PostAuthor;
import kr.megaptera.assignment.domains.model.PostId;
import kr.megaptera.assignment.domains.model.PostTitle;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @DisplayName("게시물에 이미 존재하는 댓글 삭제")
    void delete() {
        given(commentRepository.findById(CommentId.of("3")))
                .willReturn(
                        Optional.of(new Comment(
                                CommentId.of("3"),
                                new Post(
                                        PostId.of("1"),
                                        PostTitle.of("원글"),
                                        PostAuthor.of("원글쓴이"),
                                        MultilineText.of("원글 내용")),
                                CommentAuthor.of("케이"),
                                MultilineText.of("감사합니다!\n운영자님")
                        ))
                );

        CommentDto commentDto = deleteCommentService.deleteComment("3");

        verify(commentRepository).delete(any(Comment.class));

        assertThat(commentDto.getContent()).contains("운영자님");
        assertThrows(CommentNotFound.class, () -> deleteCommentService.deleteComment("4"));
    }

}
