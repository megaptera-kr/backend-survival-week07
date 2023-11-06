package kr.megaptera.assignment.models.comments;

import jakarta.persistence.*;
import kr.megaptera.assignment.models.columns.Author;
import kr.megaptera.assignment.models.columns.Content;

import java.util.UUID;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private UUID postId;
    @Column
    private Author author;
    @Column
    private Content content;

    private Comment() {
    }

    public Comment(Author author, Content content) {
        this.author = author;
        this.content = content;
    }

    public Comment(UUID id, UUID postId, Author author, Content content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public UUID getPostId() {
        return postId;
    }

    public Author getAuthor() {
        return author;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
