package kr.megaptera.assignment.domains.dto;

public class CommentUpdateDto {

    private String content;

    public CommentUpdateDto() {
    }

    public CommentUpdateDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
