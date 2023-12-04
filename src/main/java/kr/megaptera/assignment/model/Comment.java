package kr.megaptera.assignment.model;

import jakarta.persistence.*;
import kr.megaptera.assignment.dto.*;

@Entity //JPA에서 관리하겠다!
@Table(name= "comment")
public class Comment {
    @Id
    private String id;
    @Column
    private String author;
    @Column
    private String content;
    @ManyToOne
    @JoinColumn(name = "post_id") // 이 부분이 데이터베이스의 post_id 컬럼과 매핑됩니다.
    private Post post;

    @PrePersist
    private void ensureId() {
        id = TsidGenerator.generateTsid();
    }

    public Comment(String id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment(String id, String author, String content, Post post) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.post = post;
    }

    public Comment(String author, String content) {
        this.author = author;
        this.content = content;
    }
    public Comment(String content) {
        this.content = content;
    }

    public Comment() {
    }

    public Post getPost() {
        return post;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }


    public CommentResponse toCommentResponse() {
        return new CommentResponse(this.getId(), this.getAuthor(), this.getContent());
    }

    public void fromCreateRequest(CommentCreateRequest request, Post post) {
        this.setAuthor(request.getAuthor());
        this.setContent(request.getContent());
        this.setPost(post);
    }
    public void fromUpdateRequest(CommentUpdateRequest request) {
        this.content = request.getContent();
    }
}
