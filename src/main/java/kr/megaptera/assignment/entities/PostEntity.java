package kr.megaptera.assignment.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.megaptera.assignment.utils.NewIdGenerator;

@Entity
@Table(name = "post")
public class PostEntity implements Cloneable {

  @Id
  @Column
  private String id;
  @Column
  private String title;
  @Column
  private String author;
  @Column
  private String content;

  public PostEntity() {
  }

  public PostEntity(String id, String title, String author, String content) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.content = content;
  }

  public PostEntity(String title, String author, String content) {
    this.id = NewIdGenerator.getNewPostId();
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

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
