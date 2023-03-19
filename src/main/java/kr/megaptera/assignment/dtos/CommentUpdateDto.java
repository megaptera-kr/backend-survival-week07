package kr.megaptera.assignment.dtos;

public class CommentUpdateDto {
    private String postId;
    private String content;

    public CommentUpdateDto(String postId, String content) {
        this.postId = postId;
        this.content = content;
    }

    public String getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }
}
