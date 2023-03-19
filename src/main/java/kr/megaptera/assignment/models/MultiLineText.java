package kr.megaptera.assignment.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Embeddable
public class MultiLineText {
    @Column(name = "content")
    private String content;

    private List<String> lines;

    public MultiLineText(String value){
        this.lines = Arrays.asList(value.split("\n"));
    }

    public MultiLineText() {

    }

    public static MultiLineText of(String text){
        return new MultiLineText(text);
    }

    @Override
    public String toString() {
        return String.join("\n", lines);
    }

    @Override
    public boolean equals(Object o) {
        MultiLineText that = (MultiLineText) o;

        return Objects.equals(lines, that.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lines);
    }
}
