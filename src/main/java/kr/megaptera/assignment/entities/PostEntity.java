package kr.megaptera.assignment.entities;

import jakarta.persistence.*;
import kr.megaptera.assignment.vos.Author;
import kr.megaptera.assignment.vos.CommentId;
import kr.megaptera.assignment.vos.PostContent;
import kr.megaptera.assignment.vos.PostId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Entity
@Table(name = "posts")
public class PostEntity {
    @Id
    private PostId id;
    private String title;
    private Author author;
    private PostContent content;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    @OrderBy("commentId")
    private List<CommentEntity> comments = new ArrayList<>();

    public PostEntity() {
    }

    protected PostEntity(PostId id, String title, Author author, PostContent content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public PostContent getContent() {
        return content;
    }

    public List<CommentEntity> getComments() {
        return new ArrayList<>(comments);
    }

    public Optional<CommentEntity> getComment(
            CommentId commentId
    ) {
        return comments.stream()
                .filter(d -> d.getCommentId().equals(commentId))
                .findFirst();
    }

    public void addComment(Author author, String content) {
        CommentEntity commentEntity = CommentEntity.create(this.id, author, content);
        this.comments.add(commentEntity);
    }

    public void removeComment(CommentId commentId) {
        Optional<CommentEntity> comment = comments.stream()
                .filter(d -> d.getCommentId().equals(commentId))
                .findFirst();

        if (comment.isEmpty()) {
            return;
        }

        comments.remove(comment.get());
    }

    public void updateComment(CommentId commentId, String content) {
        Optional<CommentEntity> comment = comments.stream()
                .filter(d -> d.getCommentId().equals(commentId))
                .findFirst();

        if (comment.isEmpty()) {
            return;
        }

        comment.get().updateContent(content);
    }

    public static PostEntity of(PostId id, String title, Author author, PostContent content) {
        return new PostEntity(id, title, author, content);
    }

    public static PostEntity create(String title, Author author, PostContent content) {
        PostId postId = PostId.generate();
        return new PostEntity(postId, title, author, content);
    }

    public void updatePost(String title, PostContent content) {
        this.title = title;
        this.content = content;
    }
}
