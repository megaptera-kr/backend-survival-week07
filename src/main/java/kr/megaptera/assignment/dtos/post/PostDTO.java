package kr.megaptera.assignment.dtos.post;

import kr.megaptera.assignment.models.*;

public class PostDTO {
    private String id;
    private String title;
    private String author;
    private String content;

    public PostDTO() {
    }

    public PostDTO(String id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostDTO(Post post) {
        this(
                post.id(),
                post.title().toString(),
                post.author().toString(),
                post.content().toString());
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
