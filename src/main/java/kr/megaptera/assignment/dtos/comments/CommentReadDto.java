package kr.megaptera.assignment.dtos.comments;

import kr.megaptera.assignment.models.comments.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentReadDto {
    private String id;
    private String author;
    private String content;

    // 객체관의 관계는 항상 factory 로 진행 하는게 좋지만... (연관성을 없애야함)
    public CommentReadDto(Comment comment) {
        id = comment.getId();
        author = comment.getAuthor();
        content = comment.getContent();
    }
}
