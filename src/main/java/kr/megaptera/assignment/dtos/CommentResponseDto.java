package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.entities.CommentEntity;

public class CommentResponseDto {
  private String id;
  private String author;
  private String content;

  public CommentResponseDto() {
  }

  public CommentResponseDto(String id, String author, String content) {
    this.id = id;
    this.author = author;
    this.content = content;
  }

  public static CommentResponseDto of(CommentEntity commentEntity) {
    return new CommentResponseDto(commentEntity.getId(), commentEntity.getAuthor(),
        commentEntity.getContent());
  }

  public String getId() {
    return id;
  }

  public String getAuthor() {
    return author;
  }

  public String getContent() {
    return content;
  }
}
