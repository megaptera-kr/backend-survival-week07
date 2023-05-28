package kr.megaptera.assignment.models;

import java.io.Serializable;

public class Title implements Serializable {

    private String title;

    public Title(String title) {
        this.title = title;
    }

    public static Title of (String title) {
        return new Title(title);
    }

    @Override
    public String toString() {
        return title;
    }
}
