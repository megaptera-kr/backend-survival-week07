package kr.megaptera.assignment.models;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "comments")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @EmbeddedId
    private CommentId id;

    @AttributeOverride(name = "id", column = @Column(name = "postid"))
    private PostId postId;

    private String author;

    private String content;

    public Comment(PostId postId, String author, String content) {
        this.id = CommentId.generate();
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }

}
