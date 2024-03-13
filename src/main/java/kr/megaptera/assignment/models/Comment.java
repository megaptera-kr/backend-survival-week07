package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;
import jakarta.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "post_id")
    private String postId;
    @Column(name = "author")
    @Embedded
    private Author author;
    @Column(name = "content")
    @Embedded
    private Content content;

    @ManyToOne
    @JoinColumn(name = "post_id", insertable = false, updatable = false)
    private Post post;

    private Comment() {
    }

    public Comment(String postId, Author author, Content content) {
        this.id = TsidCreator.getTsid().toString();
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public Comment(String id, String postId, Author author, Content content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }
}
