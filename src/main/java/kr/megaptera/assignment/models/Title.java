package kr.megaptera.assignment.models;

import jakarta.persistence.*;

import java.util.*;

@Embeddable
public class Title {
    
    @Column(name = "title")
    private String title;

    private Title() {
    }

    public Title(String title) {
        this.title = title;
    }

    public static Title of(String title) {
        return new Title(title);
    }

    public String title() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Title title1 = (Title) o;
        return Objects.equals(title, title1.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
