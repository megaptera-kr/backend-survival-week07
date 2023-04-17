package kr.megaptera.assignment.dtos;

public class CreateCommentRequest {

    private String content;
    private String author;

    public CreateCommentRequest() {
    }

    public CreateCommentRequest(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
}
