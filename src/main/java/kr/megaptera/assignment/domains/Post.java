package kr.megaptera.assignment.domains;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @EmbeddedId
    private PostId id;

    private String title;

    private String author;

    private String content;

    g

    public Post() {
    }

    public Post(PostId id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
