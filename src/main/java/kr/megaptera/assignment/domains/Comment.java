package kr.megaptera.assignment.domains;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @EmbeddedId
    private CommentId id;

    private String author;

    private String content;

    private PostId postId;

    public Comment() {
    }

    public Comment(CommentId id, String author, String content, PostId postId) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.postId = postId;
    }

    public Comment(String author, String content, PostId postId) {
        this.author = author;
        this.content = content;
        this.postId = postId;
    }

    public void update(String content) {
        this.content = content;
    }

    public CommentId getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public PostId getPostId() {
        return postId;
    }
}
