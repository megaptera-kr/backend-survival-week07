package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.models.Post;

public class PostDto {
    private String id;
    private String author;
    private String title;
    private String content;

    public PostDto() {
    }

    public PostDto(String id, String author, String title, String content) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public PostDto(Post post) {
        this(post.id().toString(), post.author(), post.title(), post.content().toString());
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
