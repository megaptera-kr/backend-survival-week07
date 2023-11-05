package kr.megaptera.assignment.models.posts;

import jakarta.persistence.*;
import kr.megaptera.assignment.models.comments.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private String content;

    private Post() {
    }

    public Post(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(UUID id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public UUID getId() {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "postId")
    private final List<Comment> commentList = new ArrayList<>();

    public void addComment(String author, String content) {
        Comment newComment = new Comment(author, content);

        this.commentList.add(newComment);
    }

    public List<Comment> findAllComments() {
        return new ArrayList<>(this.commentList);
    }

    public void deleteComment(UUID commentId) {
        this.commentList.removeIf(comment -> comment.getId().equals(commentId));
    }
}
