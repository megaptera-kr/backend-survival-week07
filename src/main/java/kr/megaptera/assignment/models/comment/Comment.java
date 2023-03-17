package kr.megaptera.assignment.models.comment;

import jakarta.persistence.*;
import kr.megaptera.assignment.models.post.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "id")
    private CommentId id;
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "post_id"))
    private PostId postId;
    private String author;
    private String content;

    public Comment() {
    }

    public Comment(CommentId id,
                   PostId postId,
                   String author,
                   String content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public Comment(PostId postId, String author, String content) {
        this.id = CommentId.generate();
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public CommentId id() {
        return id;
    }

    public PostId postId() {
        return postId;
    }

    public String author() {
        return author;
    }

    public String content() {
        return content;
    }

    public void update(String content) {
        this.content = content;
    }
}
