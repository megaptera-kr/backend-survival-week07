package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.models.Comment;

public class CommentDto {
    String id;
    String postId;
    String content;
    String author;

    public CommentDto(String id, String postId, String content, String author) {
        this.id = id;
        this.postId = postId;
        this.content = content;
        this.author = author;
    }

    public CommentDto() {
    }

    public CommentDto(String postId, String content, String author) {
        this.postId = postId;
        this.content = content;
        this.author = author;
    }

    public CommentDto(String postId) {
        this.postId = postId;
    }

    public CommentDto(Comment comment) {
        this.id = comment.id();
        this.postId = comment.postId();
        this.content = comment.content().toString();
        this.author = comment.author().toString();
    }

    public CommentDto(String id, String postId) {
        this.id = id;
        this.postId = postId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
