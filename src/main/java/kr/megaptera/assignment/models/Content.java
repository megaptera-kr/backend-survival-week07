package kr.megaptera.assignment.models;

import java.io.Serializable;

public class Content implements Serializable {

    private String content;

    public Content(String content) {
        this.content = content;
    }

    public static Content of (String content) {
        return new Content(content);
    }

    @Override
    public String toString() {
        return content;
    }
}
