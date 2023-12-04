package kr.megaptera.assignment.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 키생성 DB에 위임
    private Long id;

    private Long postId;

    private String author;

    @Embedded
    private MultilineText content;

    public Comment() {
    }

    public Comment(Long postId, String author, MultilineText content) {
        this.postId = postId;
        this.author = author;
        this.content = content;
    }


    public Comment(Long id, Long postId, String author, MultilineText content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public Long id() {
        return id;
    }

    public Long postId() {
        return postId;
    }

    public String author() {
        return author;
    }

    public MultilineText content() {
        return content;
    }

    public void update(MultilineText content) {
        this.content = content;
    }
}
