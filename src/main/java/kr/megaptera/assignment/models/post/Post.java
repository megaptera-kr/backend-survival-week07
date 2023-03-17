package kr.megaptera.assignment.models.post;

import jakarta.persistence.*;
import kr.megaptera.assignment.models.comment.*;

import java.util.*;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @Column(name = "id")
    private PostId postId;

    private String title;

    private String author;

    private MultilineText content;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private List<Comment> comments;

    public Post() {
    }

    public Post(PostId postId,
                String title,
                String author,
                MultilineText content) {
        this.postId = postId;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(String title, String author, MultilineText content) {
        this.postId = PostId.generate();
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostId id() {
        return postId;
    }

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public MultilineText content() {
        return content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = MultilineText.of(content);
    }
}
