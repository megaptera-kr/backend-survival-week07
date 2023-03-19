package kr.megaptera.assignment.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.megaptera.assignment.utils.NewIdGenerator;

@Entity
@Table(name = "comment")
public class CommentEntity {

  @Id
  @Column
  private String id;
  @Column
  private String postId;
  @Column
  private String author;
  @Column
  private String content;

  public CommentEntity() {
  }

  public CommentEntity(String id, String postId, String author, String content) {
    this.id = id;
    this.postId = postId;
    this.author = author;
    this.content = content;
  }

  public CommentEntity(String postId, String author, String content) {
    this.id = NewIdGenerator.getNewCommentId();
    this.postId = postId;
    this.author = author;
    this.content = content;
  }

  public String getId() {
    return id;
  }

  public String getPostId() {
    return postId;
  }

  public String getAuthor() {
    return author;
  }

  public String getContent() {
    return content;
  }

  public void update(String content) {
    this.content = content;
  }
}
