package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.models.PostId;

public class CreateCommentDto {
    private String postId;
    private String author;
    private String content;

    public CreateCommentDto(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
