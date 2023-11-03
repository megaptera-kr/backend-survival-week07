package kr.megaptera.assignment.dtos.comments;

public class UpdateCommentDto {
    String content;

    public UpdateCommentDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
