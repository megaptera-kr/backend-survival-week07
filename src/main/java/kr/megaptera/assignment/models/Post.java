package kr.megaptera.assignment.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import kr.megaptera.assignment.dtos.PostUpdateDto;

@Entity
public class Post {
    @Id
    @Embedded
    private PostId id;

    private String author;

    private String title;

    @Embedded
    private MultilineText content;

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
}
