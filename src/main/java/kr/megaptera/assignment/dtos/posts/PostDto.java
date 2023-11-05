package kr.megaptera.assignment.dtos.posts;

import kr.megaptera.assignment.models.posts.Post;

public class PostDto {
    private String id;
    private String title;
    private String author;
    private String content;

    public PostDto(String id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public static PostDto of(Post post) {
        return new PostDto(
                post.getId().toString(),
                post.getTitle().toString(),
                post.getAuthor().toString(),
                post.getContent().toString()
        );
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
