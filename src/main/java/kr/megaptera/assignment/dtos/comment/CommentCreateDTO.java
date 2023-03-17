package kr.megaptera.assignment.dtos.comment;

public class CommentCreateDTO {
    private String author;
    private String content;

    public CommentCreateDTO() {
    }

    public CommentCreateDTO(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
