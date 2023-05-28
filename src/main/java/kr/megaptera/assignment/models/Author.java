package kr.megaptera.assignment.models;

import java.io.Serializable;

public class Author implements Serializable {

    private String author;

    public Author(String author) {
        this.author = author;
    }

    public static Author of (String author) {
        return new Author(author);
    }

    @Override
    public String toString() {
        return author;
    }
}
