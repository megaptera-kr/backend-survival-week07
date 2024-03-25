package kr.megaptera.assignment.dtos;

public class CommentUpdateDto {
    private String content;

    public CommentUpdateDto(String content) {
        this.content = content;
    }

    private CommentUpdateDto() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
