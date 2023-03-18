package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.*;
import jakarta.persistence.*;
import kr.megaptera.assignment.dtos.comment.*;
import kr.megaptera.assignment.dtos.post.*;
import kr.megaptera.assignment.exceptions.*;

import java.util.*;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    private String id;
    @Embedded
    private Title title;
    @Embedded
    private Author author;
    @Embedded
    private Content content;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    @OrderBy("id")
    private List<Comment> comments;

    public List<Comment> list() {
        return new ArrayList<>(comments);
    }

    public Comment addComment(CommentCreateDTO commentCreateDTO) {
        Comment comment = new Comment(
                this.id,
                Author.of(commentCreateDTO.getAuthor()),
                Content.of(commentCreateDTO.getContent())
        );
        comments.add(comment);
        return comment;
    }

    private String generate() {
        return TsidCreator.getTsid().toString();
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", title=" + title +
                ", author=" + author +
                ", content=" + content +
                ", comments=" + comments +
                '}';
    }

    private Post() {
    }

    public Post(String id, Title title, Author author, Content content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.comments = new ArrayList<>();
    }

    public Post(Title title, Author author, Content content) {
        this.id = generate();
        this.title = title;
        this.author = author;
        this.content = content;
        this.comments = new ArrayList<>();
    }

    public String id() {
        return id;
    }

    public Title title() {
        return title;
    }

    public Author author() {
        return author;
    }

    public Content content() {
        return content;
    }


    public void update(PostUpdateDTO postUpdateDTO) {
        this.title = Title.of(postUpdateDTO.getTitle());
        this.content = Content.of(postUpdateDTO.getContent());
    }

    public void updateComment(String id,
                              CommentUpdateDTO commentUpdateDTO) {
        Comment comment = comments.stream()
                .filter(t -> t.id().equals(id))
                .findFirst()
                .get();
        if (comment == null) {
            throw new CommentNotFound();
        }
        comment.update(commentUpdateDTO);
    }

    public void removeComment(String id) {
        Comment comment = comments.stream()
                .filter(t -> t.id().equals(id))
                .findFirst()
                .get();
        if (comment == null) {
            throw new CommentNotFound();
        }
        comments.remove(comment);
    }
}
