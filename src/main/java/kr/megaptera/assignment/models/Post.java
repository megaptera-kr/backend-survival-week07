package kr.megaptera.assignment.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 키생성 DB에 위임
    private Long id;

    private String title;

    private String author;

    @Embedded
    private MultilineText content;

    public Post(Long id, String title, String author, MultilineText content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(String title, String author, MultilineText content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post() {

    }

    public Long id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public MultilineText content() {
        return content;
    }

    public void update(String title, MultilineText content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content=" + content +
                '}';
    }
}
