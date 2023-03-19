package kr.megaptera.assignment.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.megaptera.assignment.dtos.PostDto;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @Column(name = "post_id")
    @Embedded
    PostId id;

    @Column(name = "title")
    String title;

    @Column(name = "author")
    String author;

    @Column(name = "content")
    String content;

    public PostId id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public String content() {
        return content;
    }

    public Post() {
    }

    public Post(PostId id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(String title, String author, String content) {
        this.id = PostId.generate();
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostDto toDto() {
        return new PostDto(
            this.id.toString(),
            this.title(),
            this.author(),
            this.content());
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
