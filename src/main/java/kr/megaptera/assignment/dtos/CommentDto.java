package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.entities.Comment;
import kr.megaptera.assignment.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private String id;

    private String author;
    private String content;
    private String postComment;
    public CommentDto(Comment comment) {
        this.id = comment.getId().toString();
        this.author = comment.getAuthor();
        this.postComment = comment.getContent();
    }
}
