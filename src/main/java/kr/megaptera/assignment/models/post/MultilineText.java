package kr.megaptera.assignment.models.post;

import jakarta.persistence.*;

import java.util.*;

@Embeddable
public class MultilineText {

    private List<String> content;

    public MultilineText() {
    }

    public MultilineText(String content) {
        this.content = new ArrayList<>(List.of(content.split("\n")));
    }

    public static MultilineText of(String content) {
        return new MultilineText(content);
    }

    @Override
    public String toString() {
        return String.join("\n", content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultilineText that = (MultilineText) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
