package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.models.Post;

public class PostDto {
    String id;
    String title;
    String content;
    String author;

    public PostDto(String id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public PostDto(Post post) {
        this.id = post.id();
        this.title = post.title().toString();
        this.content = post.content().toString();
        this.author = post.author().toString();
    }

    public PostDto(String id) {
        this.id = id;
    }

    public PostDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
