package kr.megaptera.assignment.dtos.posts;

import kr.megaptera.assignment.models.posts.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostReadDto {
    private String id;
    private String title;
    private String author;
    private String content;

    public PostReadDto(Post post){
        id = post.getId().toString();
        title = post.getTitle();
        author = post.getAuthor();
        content = post.getContent().toString();
    }
}
