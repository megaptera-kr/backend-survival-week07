package kr.megaptera.assignment.dtos.comments;

import kr.megaptera.assignment.models.comments.Comment;

public class CommentDto {
    String id;
    String postId;
    String author;
    String content;

    public CommentDto(String id, String postId, String author, String content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
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

    public static CommentDto of(Comment comment) {
        return new CommentDto(
                comment.getId().toString(),
                comment.getPostId().toString(),
                comment.getAuthor().toString(),
                comment.getContent().toString()
        );
    }
}
