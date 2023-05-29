package kr.megaptera.assignment.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {

    @EmbeddedId
    private CommentId id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "post_id"))
    private PostId postId;

    private Author author;

    private Content content;

    public Comment(PostId postId, Author author, Content content) {
        this.id = CommentId.generate();
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    protected Comment() {

    }

    public Comment(CommentId id, PostId postId, Author author, Content content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public void update(Content content) {
        this.content = content;
    }

    public CommentId Id() {
        return id;
    }

    public PostId PostId() {
        return postId;
    }

    public Author Author() {
        return author;
    }

    public Content Content() {
        return content;
    }

    public boolean isEqualId(CommentId commentId) {
        return this.id.equals(commentId);
    }

    public boolean isEqualPostId(PostId postId) {
        return this.postId.equals(postId);
    }

    public void createId(CommentId commentId) {
        this.id = commentId;
    }
}
