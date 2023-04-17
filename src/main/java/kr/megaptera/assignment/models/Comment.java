package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.*;
import jakarta.persistence.*;
import kr.megaptera.assignment.dtos.comment.*;

import java.util.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    private String id;
    @Column(name = "post_id")
    private String postId;
    @Embedded
    private Author author;
    @Embedded
    private Content content;

    private String generate() {
        return TsidCreator.getTsid().toString();
    }

    private Comment() {
    }

    public Comment(String id, String postId, Author author, Content content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public Comment(String postId, Author author, Content content) {
        this.id = generate();
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public String id() {
        return id;
    }

    public String postId() {
        return postId;
    }

    public Author author() {
        return author;
    }

    public Content content() {
        return content;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(postId, comment.postId) && Objects.equals(author, comment.author) && Objects.equals(content, comment.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postId, author, content);
    }

    public void update(CommentUpdateDTO commentUpdateDTO) {
        this.content = Content.of(commentUpdateDTO.getContent());
    }
}
