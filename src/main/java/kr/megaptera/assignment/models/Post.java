package kr.megaptera.assignment.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import kr.megaptera.assignment.dtos.PostUpdateDto;

@Entity
public class Post {
    @EmbeddedId
    private PostId id;

    private String author;

    private String title;

    @Embedded
    private MultilineText content;

    public Post() {
    }

    public Post(PostId id, String author, String title, MultilineText content) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public Post(String author, String title, MultilineText content) {
        this.id = PostId.generate();
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public void update(PostUpdateDto postUpdateDto) {
        this.title = postUpdateDto.getTitle();
        this.content = MultilineText.of(postUpdateDto.getContent());
    }

    public PostId id() {
        return id;
    }

    public String author() {
        return author;
    }

    public String title() {
        return title;
    }

    public MultilineText content() {
        return content;
    }

    @Override
    public String toString() {
        return "Post{" +
            "id=" + id +
            ", author='" + author + '\'' +
            ", title='" + title + '\'' +
            ", content=" + content +
            '}';
    }
}
