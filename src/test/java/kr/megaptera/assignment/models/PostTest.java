package kr.megaptera.assignment.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {
    @Test
    void creation() {
        Post post = new Post(
                "TITLE",
                "AUTHOR",
                new MultilineText("CONTENT")
        );

        assertThat(post.title()).isEqualTo("TITLE");
        assertThat(post.author()).isEqualTo("AUTHOR");
        assertThat(post.content().toString()).isEqualTo("CONTENT");
    }

    @Test
    void update() {
        Post post = new Post(
                new PostId("001POST"),
                "TITLE",
                "AUTHOR",
                new MultilineText("CONTENT")
        );

        post.update("UPDATED_TITLE", new MultilineText("UPDATED_CONTENT"));

        assertThat(post.title()).isEqualTo("UPDATED_TITLE");
        assertThat(post.author()).isEqualTo("AUTHOR");
        assertThat(post.content().toString()).isEqualTo("UPDATED_CONTENT");
    }
}