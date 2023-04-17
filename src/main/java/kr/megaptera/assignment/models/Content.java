package kr.megaptera.assignment.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Content {
    @Column(name = "content", columnDefinition = "TEXT")
    private String text;

    protected Content() {
    }

    public Content(String text) {
        this.text = text;
    }

    public static Content of(String text) {
        return new Content(text);
    }

    @Override
    public String toString() {
        return this.text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Content content = (Content) o;
        return Objects.equals(text, content.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
