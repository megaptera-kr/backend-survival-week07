package kr.megaptera.assignment.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.megaptera.assignment.vos.Author;
import kr.megaptera.assignment.vos.CommentId;
import kr.megaptera.assignment.vos.PostId;

@Entity
@Table(name = "comments")
public class CommentEntity {
    @Id
    private CommentId commentId;
    private PostId postId;
    private Author author;
    private String content;

    protected CommentEntity() {
    }

    protected CommentEntity(CommentId commentId, PostId postId, Author author, String content) {
        this.commentId = commentId;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public CommentId getCommentId() {
        return commentId;
    }

    public PostId getPostId() {
        return postId;
    }

    public Author getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public static CommentEntity of(CommentId id, PostId postId, Author author, String content) {
        return new CommentEntity(id, postId, author, content);
    }

    public static CommentEntity create(PostId postId, Author author, String content) {
        CommentId id = CommentId.generate();
        return new CommentEntity(id, postId, author, content);
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
