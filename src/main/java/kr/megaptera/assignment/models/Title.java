package kr.megaptera.assignment.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Title {
    private String title;

    private Title() {
    }

    public Title(String title) {
        this.title = title;
    }

    public void title(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
