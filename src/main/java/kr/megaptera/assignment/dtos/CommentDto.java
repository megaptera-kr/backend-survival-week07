package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;

public class CommentDto {
    private String id;
    private String author;
    private String content;

    public CommentDto() {
    }

    public CommentDto(String id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public CommentDto(Comment comment) {
        this.id = comment.id().toString();
        this.author = comment.author();
        this.content = comment.content();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
