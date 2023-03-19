package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.models.Comment;

public class CommentDto {
    private String postId;
    private String author;
    private String content;

    public CommentDto(String postId, String author, String content) {
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public CommentDto(Comment comment){
        this(comment.postId().toString(), comment.author(), comment.content());
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
