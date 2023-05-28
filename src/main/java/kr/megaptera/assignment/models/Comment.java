package kr.megaptera.assignment.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import kr.megaptera.assignment.dtos.CommentUpdateDto;

@Entity
public class Comment {
    @Id
    @Embedded
    private CommentId id;

    @Embedded
    private PostId postId;

    private String author;

    @Embedded
    private MultilineText content;

    public Comment() {
    }

    public Comment(CommentId id, PostId postId, String author, MultilineText content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public Comment(PostId postId, String author, String content) {
        this.id = CommentId.generate();
        this.postId = postId;
        this.author = author;
        this.content = MultilineText.of(content);
    }

    public void update(CommentUpdateDto commentUpdateDto) {
        this.content = MultilineText.of(commentUpdateDto.getContent());
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

    public MultilineText content() {
        return content;
    }
}
