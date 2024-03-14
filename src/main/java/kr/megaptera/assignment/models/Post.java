package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;
import jakarta.persistence.*;

@Entity
@Table(name = "POSTS")
public class Post {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "author")
    private Author author;
    @Column(name = "title")
    @Embedded
    private Title title;
    @Column(name = "content")
    @Embedded
    private Content content;

    private Post() {
    }

    public Post(Author author, Title title, Content content) {
        this.id = TsidCreator.getTsid().toString();
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public Post(String id, Author author, Title title, Content content) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public String id() {
        return id;
    }

    public Author author() {
        return author;
    }

    public Title title() {
        return title;
    }

    public Content content() {
        return content;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
