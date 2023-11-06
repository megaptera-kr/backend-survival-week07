package kr.megaptera.assignment.dtos.comments;

public class CreateCommentDto {
    String author;
    String content;

    public CreateCommentDto(String author, String content) {
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
