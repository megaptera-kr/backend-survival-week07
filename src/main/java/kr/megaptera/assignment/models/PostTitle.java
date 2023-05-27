package kr.megaptera.assignment.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class PostTitle {
    @Column(name = "title")
    private String title;

    public PostTitle() {
    }

    public PostTitle(String title) {
        this.title = title;
    }

    public static PostTitle of(String title) {
        return new PostTitle(title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostTitle postTitle = (PostTitle) o;
        return Objects.equals(title, postTitle.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return title;
    }
}
