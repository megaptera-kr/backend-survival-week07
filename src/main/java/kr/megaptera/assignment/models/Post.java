package kr.megaptera.assignment.models;

import jakarta.persistence.*;
import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDto;

@Entity
@Table(name = "post")
public class Post {
    @EmbeddedId
    private PostId id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "content")
    private String content;

    public Post(){

    }

    public Post(PostDto postDto){
        this(postDto.getTitle(), postDto.getAuthor(), postDto.getContent());
    }

    public Post(String title, String author, String content) {
        this.id = PostId.generate();
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(PostCreateDto postCreateDto) {
        this(postCreateDto.getTitle(), postCreateDto.getAuthor(), postCreateDto.getContent());
    }

    public PostId id(){
        return id;
    }

    public String title(){
        return title;
    }

    public String author(){
        return author;
    }

    public String content(){
        return content.toString();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
