package kr.megaptera.assignment.models.comments;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private UUID postId;
    @Column
    private String author;
    @Column
    private String content;

    private Comment() {
    }

    public Comment(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public Comment(UUID id, UUID postId, String author, String content) {
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

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
