package kr.megaptera.assignment.dtos;

public class CommentUpdateDto {

    String content;

    public CommentUpdateDto() {
    }

    public CommentUpdateDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
