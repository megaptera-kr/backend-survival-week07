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
    @Embedded
    private Title title;
    @Embedded
    private Author author;
    @Embedded
    private Content content;

    private Post() {
    }

    public Post(Title title, Author author, Content content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(UUID id, Title title, Author author, Content content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Content getContent() {
        return content;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setContent(Content content) {
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
