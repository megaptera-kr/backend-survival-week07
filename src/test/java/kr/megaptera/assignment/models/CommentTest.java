package kr.megaptera.assignment.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommentTest {
    @Test
    void creation() {
        PostId postId = new PostId("001POST");

        Comment comment = new Comment(
                postId,
                "AUTHOR",
                "CONTENT"
        );

        assertThat(comment.postId()).isEqualTo(postId);
        assertThat(comment.author()).isEqualTo("AUTHOR");
        assertThat(comment.content()).isEqualTo("CONTENT");
    }

    @Test
    void update() {
        PostId postId = new PostId("001POST");

        Comment comment = new Comment(
                postId,
                "AUTHOR",
                "COMMENT_CONTENT"
        );

        comment.update("UPDATED_COMMENT_CONTENT");

        assertThat(comment.content()).isEqualTo("UPDATED_COMMENT_CONTENT");
    }
}