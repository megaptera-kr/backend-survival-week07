package kr.megaptera.assignment.model;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.domains.dto.CommentCreateDto;
import kr.megaptera.assignment.domains.dto.CommentUpdateDto;
import kr.megaptera.assignment.domains.model.Comment;
import kr.megaptera.assignment.domains.model.CommentId;
import kr.megaptera.assignment.domains.model.MultilineText;
import kr.megaptera.assignment.domains.model.Post;
import kr.megaptera.assignment.domains.model.PostAuthor;
import kr.megaptera.assignment.domains.model.PostId;
import kr.megaptera.assignment.domains.model.PostTitle;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class CommentTest {

    @Test
    void creation() {
        CommentCreateDto CommentCreateDto = new CommentCreateDto("글쓴이입니다", "내용입니다\n\n내용");
        Comment Comment = new Comment(
                new Post(
                        PostId.of("3"),
                        PostTitle.of("원글"),
                        PostAuthor.of("원글쓴이"),
                        MultilineText.of("원글 내용")), CommentCreateDto);

        assertThat(Comment.id()).isNotNull();
        assertThat(Comment.post().id().getId()).contains("3");
        assertThat(Comment.author().getAuthor()).contains("글쓴이");
        assertThat(Comment.content().toString()).contains("내용");
    }

    @Test
    void updateComment() {
        CommentCreateDto CommentCreateDto = new CommentCreateDto("글쓴이입니다", "내용입니다\n\n내용");
        Comment Comment = new Comment(
                new Post(
                        PostId.of("3"),
                        PostTitle.of("원글"),
                        PostAuthor.of("원글쓴이"),
                        MultilineText.of("원글 내용")), CommentCreateDto);
        CommentId CommentId = Comment.id();

        CommentUpdateDto CommentUpdateDto = new CommentUpdateDto("새로운 내용~입니닷");
        Comment.updateComment(CommentUpdateDto);

        assertThat(Comment.id().getId()).isEqualTo(CommentId.getId());
        assertThat(Comment.post().id().getId()).contains("3");
        assertThat(Comment.author().getAuthor()).contains("글쓴이");
        assertThat(Comment.content().toString()).contains("~입니닷");
    }
}