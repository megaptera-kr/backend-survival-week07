package kr.megaptera.assignment.models;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Author {
    private String author;

    public Author(String author) {
        this.author = author;
    }

    private Author() {

    }

    public void author(String author) {
        this.author = author;
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
