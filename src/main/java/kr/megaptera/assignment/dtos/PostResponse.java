package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.models.Author;
import kr.megaptera.assignment.models.Content;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.Title;

public class PostResponse {

    private String id;
    private String title;
    private String author;
    private String content;

    public PostResponse(String id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostResponse(Post post) {
        this.id = post.getId().toString();
        this.title = post.getTitle().toString();
        this.author = post.getAuthor().toString();
        this.content = post.getContent().toString();
    }

    public Post toEntity() {
        return new Post(Long.parseLong(this.id), Title.of(this.title), Author.of(this.author),
                Content.of(this.content));
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
