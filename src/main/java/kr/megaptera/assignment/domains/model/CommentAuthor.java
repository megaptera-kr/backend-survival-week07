package kr.megaptera.assignment.domains.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CommentAuthor {

    @Column
    private String author;

    private CommentAuthor() {
    }

    public CommentAuthor(String author) {
        this.author = author;
    }

    public static CommentAuthor of(String author) {
        return new CommentAuthor(author);
    }

    public String getAuthor() {
        return author;
    }
}
