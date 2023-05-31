package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private String id;
    private String title;
    private String author;
    private String content;
    public PostDto(Post post) {
        this(post.getId().toString(),post.getTitle(),  post.getAuthor(), post.getContent());
    }
}
