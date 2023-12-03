package kr.megaptera.assignment.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommentTest {
    @Test
    void creation() {
        Long postId = 1L;

        Comment comment = new Comment(
                postId,
                "작성자",
                MultilineText.of("댓글 내용")
        );

        assertThat(comment.postId()).isEqualTo(postId);
        assertThat(comment.author()).isEqualTo("작성자");
        assertThat(comment.content().toString()).isEqualTo("댓글 내용");
    }

    @Test
    void update() {
        Long postId = 2L;

        Comment comment = new Comment(
                postId,
                "작성자",
                MultilineText.of("댓글 내용")
        );

        comment.update(MultilineText.of("변경된 댓글"));

        assertThat(comment.content().toString()).isEqualTo("변경된 댓글");
    }
}
