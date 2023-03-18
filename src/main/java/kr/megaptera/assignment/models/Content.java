package kr.megaptera.assignment.models;

import jakarta.persistence.*;

import java.util.*;

@Embeddable
public class Content {
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    private Content() {
    }

    public Content(String content) {
        this.content = content;
    }

    public static Content of(String content) {
        return new Content(content);
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Content content1 = (Content) o;
        return Objects.equals(content, content1.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
