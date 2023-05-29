package kr.megaptera.assignment.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentTest {

    @Test
    void create() {
        PostId postId = new PostId("001POST");

        Comment comment = new Comment(
                postId,
                "작성자",
                "내용"
        );

        assertThat(comment.postId()).isEqualTo(postId);
        assertThat(comment.author()).isEqualTo("작성자");
        assertThat(comment.content()).isEqualTo("내용");
    }

    @Test
    void update() {
        PostId postId = new PostId("001POST");

        Comment comment = new Comment(
                postId,
                "작성자",
                "내용"
        );

        comment.update("변경될 댓글");

        assertThat(comment.content()).isEqualTo("변경될 댓글");
    }
}
