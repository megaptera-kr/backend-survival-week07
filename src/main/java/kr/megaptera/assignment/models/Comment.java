package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;
import jakarta.persistence.*;
import kr.megaptera.assignment.dtos.CreateCommentDto;

@Entity
@Table(name = "comment")
public class Comment {
    @EmbeddedId
    private CommentId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "postId"))
    private PostId postId;

    private String author;

    private String content;

    public Comment(CommentId id, PostId postId, String author, String content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public Comment(PostId postId, String author, String content) {
        this.id = Comment.generate();
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public Comment(CreateCommentDto createCommentDto) {
        this(PostId.of(createCommentDto.getPostId()),
                createCommentDto.getAuthor(),
                createCommentDto.getContent());
    }

    public Comment() {

    }

    public CommentId getId() {
        return id;
    }

    private static CommentId generate() {
        return CommentId.of(TsidCreator.getTsid().toString());
    }

    public PostId postId() {
        return postId;
    }

    public String author() {
        return author;
    }

    public String content() {
        return content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

