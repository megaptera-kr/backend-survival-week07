package kr.megaptera.assignment.dto;

public class CommentUpdateRequest {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CommentUpdateRequest(String content) {
        this.content = content;
    }


}
