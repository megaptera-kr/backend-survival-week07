package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.models.Author;
import kr.megaptera.assignment.models.Content;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.Title;

public class CreatePostRequest {
    private String title;
    private String author;
    private String content;

    public CreatePostRequest() {
    }

    public CreatePostRequest(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post toEntity() {
        return new Post(Title.of(title), Author.of(author), Content.of(content));
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
