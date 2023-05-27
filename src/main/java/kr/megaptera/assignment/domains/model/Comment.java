package kr.megaptera.assignment.domains.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kr.megaptera.assignment.domains.dto.CommentCreateDto;
import kr.megaptera.assignment.domains.dto.CommentUpdateDto;

@Entity
public class Comment {

    @EmbeddedId
    private CommentId id;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @Embedded
    private CommentAuthor author;

    @Embedded
    private MultilineText content;

    private Comment() {
    }

    public Comment(CommentId id, Post post, CommentAuthor author, MultilineText content) {
        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
    }

    public Comment(Post post, CommentCreateDto commentCreateDto) {
        this.id = new CommentId();
        this.post = post;
        this.author = CommentAuthor.of(commentCreateDto.getAuthor());
        this.content = MultilineText.of(commentCreateDto.getContent());
    }

    public CommentId id() {
        return this.id;
    }

    public Post post() {
        return this.post;
    }

    public CommentAuthor author() {
        return this.author;
    }

    public MultilineText content() {
        return this.content;
    }

    public void updateComment(CommentUpdateDto commentUpdateDto) {
        this.content = MultilineText.of(commentUpdateDto.getContent());
    }
}
