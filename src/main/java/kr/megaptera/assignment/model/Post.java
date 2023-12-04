package kr.megaptera.assignment.model;

import jakarta.persistence.*;
import kr.megaptera.assignment.dto.PostCreateRequest;
import kr.megaptera.assignment.dto.PostResponse;
import kr.megaptera.assignment.dto.PostUpdateRequest;

import java.util.List;

@Entity
@Table(name="post")
public class Post {
    @Id
    private String id;
    private String title;
    private String author;
    private String content;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Post() {
    }

    public Post(String id, String title, String author, String content, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.comments = comments;
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post(String id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
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

    @PrePersist
    private void ensureId() {
        id = TsidGenerator.generateTsid();
    }

    public PostResponse toPostResponse() {
        return new PostResponse(this.getId(), this.getTitle(), this.getAuthor(), this.getContent());
    }

    public Post fromCreateRequest(PostCreateRequest request) {
        return new Post(
                request.getTitle(),
                request.getAuthor(),
                request.getContent()
        );
    }
    public void fromUpdateRequest(PostUpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }
}
