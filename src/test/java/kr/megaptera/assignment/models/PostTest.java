package kr.megaptera.assignment.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {

    @Test
    void Create_POST() {

        Title title = new Title("title");
        Author author = new Author("author");
        Content content = new Content("content");

        Post post = new Post(title, author, content);

        assertThat(post.Title()).isEqualTo(title);
        assertThat(post.Author()).isEqualTo(author);
        assertThat(post.Content()).isEqualTo(content);

    }

    @Test
    void Update_POST() {

        Title title = new Title("title");
        Author author = new Author("author");
        Content content = new Content("content");

        Post post = new Post(title, author, content);

        Title title2 = new Title("title2");
        Content content2 = new Content("content2");

        post.update(title2, content2);

        assertThat(post.Title()).isEqualTo(title2);
        assertThat(post.Author()).isEqualTo(author);
        assertThat(post.Content()).isEqualTo(content2);
    }

}