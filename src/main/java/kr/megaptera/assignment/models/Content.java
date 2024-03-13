package kr.megaptera.assignment.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Content {
    private String content;

    private Content() {
    }

    public Content(String content) {
        this.content = content;
    }

    public void content(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
