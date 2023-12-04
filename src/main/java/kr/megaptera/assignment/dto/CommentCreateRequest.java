package kr.megaptera.assignment.dto;

public class CommentCreateRequest {
    public CommentCreateRequest(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    private String author;
    private String content;
}
