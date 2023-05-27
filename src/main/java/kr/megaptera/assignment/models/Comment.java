package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.megaptera.assignment.dtos.CommentDto;

import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "comment_id")
    private String id;
    private String author;
    @Embedded
    private CommentContent content;
    private PostId postId;

    public Comment() {
    }

    public Comment(String id, String author, CommentContent content) {
        this.id = id;
        this.author = author;
        this.content = content;

    }

    public Comment(String author, String content, PostId postId) {
        this.id = TsidCreator.getTsid().toString();
        this.author = author;
        this.content = CommentContent.of(content);
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(author, comment.author) && Objects.equals(content, comment.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, content);
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public CommentContent getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", content=" + content +
                '}';
    }

    public void update(CommentDto dto) {
        this.content = CommentContent.of(dto.getContent());
    }
}
