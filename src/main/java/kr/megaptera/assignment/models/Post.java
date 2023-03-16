package kr.megaptera.assignment.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Title title;
    @Embedded
    private Author author;
    @Embedded
    private Content content;

    protected Post() {
    }

    public Post(Title title, Author author, Content content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(Long id, Title title, Author author, Content content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Content getContent() {
        return content;
    }

    public void update(String title, String content) {
        this.title = Title.of(title);
        this.content = Content.of(content);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title=" + title +
                ", author=" + author +
                ", content=" + content +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id.equals(post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
