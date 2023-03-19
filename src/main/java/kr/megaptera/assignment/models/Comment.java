package kr.megaptera.assignment.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.megaptera.assignment.dtos.CommentDto;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name = "comment_id")
    @Embedded
    CommentId id;

    @Column(name = "author")
    String author;

    @Column(name = "content")
    String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post;

    public Comment() {
    }

    public Comment(String author, String content, Post post) {
        this.id = CommentId.generate();
        this.author = author;
        this.content = content;
        this.post = post;
    }

    public CommentId id() {
        return id;
    }

    public String author() {
        return author;
    }

    public String content() {
        return content;
    }

    public Post post() {
        return post;
    }

    public CommentDto toDto() {
        return new CommentDto(this.id.toString(), this.author, this.content);
    }

    public void update(String content) {
        this.content = content;
    }
}
