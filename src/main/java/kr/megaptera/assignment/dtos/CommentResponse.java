package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.models.Comment;

public class CommentResponse {
    private String id;
    private String postId;
    private String author;
    private String content;

    public CommentResponse(String id, String postId, String author, String content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public CommentResponse(Comment comment) {
        this.id = comment.getId().toString();
        this.postId = comment.getPost().getId().toString();
        this.author = comment.getAuthor().toString();
        this.content = comment.getContent().toString();
    }

    public String getId() {
        return id;
    }

    public String getPostId() {
        return postId;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
