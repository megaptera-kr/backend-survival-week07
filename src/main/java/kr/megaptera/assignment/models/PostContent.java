package kr.megaptera.assignment.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Embeddable
public class PostContent {
    @Column(name = "content")
    private List<String> content;

    public PostContent() {
    }

    public PostContent(String content) {
        this.content = Arrays.asList(content.split("\n"));
    }

    public static PostContent of(String content) {
        return new PostContent(content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostContent that = (PostContent) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return content.stream().collect(Collectors.joining("\n"));
    }
}
