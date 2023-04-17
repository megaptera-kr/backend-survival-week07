package kr.megaptera.assignment.models;

import jakarta.persistence.*;

import java.util.*;

@Embeddable
public class Author {
    @Column(name = "author")
    private String author;

    private Author() {
    }

    public Author(String author) {
        this.author = author;
    }

    public static Author of(String author) {
        return new Author(author);
    }

    public String author() {
        return author;
    }

    @Override
    public String toString() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author1 = (Author) o;
        return Objects.equals(author, author1.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author);
    }
}
