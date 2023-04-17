package kr.megaptera.assignment.dtos.comment;

public class CommentUpdateDTO {
    private String content;

    public CommentUpdateDTO() {
    }

    public CommentUpdateDTO(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
