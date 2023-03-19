package kr.megaptera.assignment.dtos;

public class CommentCreateRequestDto {
  private String author;
  private String content;

  public CommentCreateRequestDto() {
  }

  public CommentCreateRequestDto(String author, String content) {
    this.author = author;
    this.content = content;
  }

  public String getAuthor() {
    return author;
  }

  public String getContent() {
    return content;
  }
}
