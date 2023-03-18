package kr.megaptera.assignment.dtos.comment;

import kr.megaptera.assignment.models.*;

public class CommentDTO {
    private String id;
    private String author;
    private String content;

    public CommentDTO() {
    }

    public CommentDTO(String id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public CommentDTO(Comment comment) {
        this(
                comment.id(),
                comment.author().toString(),
                comment.content().toString());
    }


    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
