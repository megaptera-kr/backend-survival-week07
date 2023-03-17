package kr.megaptera.assignment.dtos.post;

public class PostCreateDTO {
    private String title;
    private String author;
    private String content;

    public PostCreateDTO() {
    }

    public PostCreateDTO(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
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
