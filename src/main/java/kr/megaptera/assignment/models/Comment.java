package kr.megaptera.assignment.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "posts_id")
    private Post post;
    @Embedded
    private Author author;
    @Embedded
    private Content content;

    protected Comment() {
    }

    public Comment(Post post, Author author, Content content) {
        this.post = post;
        this.author = author;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public Author getAuthor() {
        return author;
    }

    public Content getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
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
        Comment comment = (Comment) o;
        return id.equals(comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void updateContent(String content) {
        this.content = Content.of(content);
    }
}
