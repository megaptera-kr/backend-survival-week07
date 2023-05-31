package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kr.megaptera.assignment.dtos.PostDto;

@Entity
@Table(name = "posts")
public class Post {
    @EmbeddedId
    private PostId id;
    @Embedded
    private PostTitle title;
    @Column(name = "author")
    private String author;
    @Embedded
    private PostContent content;


    public Post() {
    }

    public Post(PostId id, PostTitle title, String author, PostContent content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(PostTitle title, String author, PostContent content) {
        this.id = PostId.of(TsidCreator.getTsid().toString());
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostId id() {
        return id;
    }

    public PostTitle title() {
        return title;
    }

    public String author() {
        return author;
    }

    public PostContent content() {
        return content;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public void update(PostDto dto) {
        this.title = PostTitle.of(dto.getTitle());
        this.content = PostContent.of(dto.getContent());
    }
}
