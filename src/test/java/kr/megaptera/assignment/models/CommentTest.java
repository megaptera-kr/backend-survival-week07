package kr.megaptera.assignment.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommentTest {

    @Test
    void Create_Comment() {
        PostId postId = new PostId("test");
        Author author = new Author("author");
        Content content = new Content("content");


        Comment comment = new Comment(postId, author, content);

        assertThat(comment.PostId()).isEqualTo(postId);
        assertThat(comment.Author()).isEqualTo(author);
        assertThat(comment.Content()).isEqualTo(content);
    }

    @Test
    void Update_Comment() {
        PostId postId = new PostId("test");
        Author author = new Author("author");
        Content content = new Content("content");


        Comment comment = new Comment(postId, author, content);

        Content content2 = new Content("content2");

        comment.update(content2);

        assertThat(comment.PostId()).isEqualTo(postId);
        assertThat(comment.Author()).isEqualTo(author);
        assertThat(comment.Content()).isEqualTo(content2);
    }
}