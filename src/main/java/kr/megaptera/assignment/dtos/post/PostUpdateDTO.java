package kr.megaptera.assignment.dtos.post;

public class PostUpdateDTO {
    private String title;
    private String content;

    public PostUpdateDTO() {
    }

    public PostUpdateDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
