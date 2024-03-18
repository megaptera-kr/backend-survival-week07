package kr.megaptera.assignment.vos;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Author implements Serializable {
    private String name;

    protected Author() {
    }

    protected Author(
            String name
    ) {
        this.name = name;
    }

    public static Author of(String author) {
        return new Author(author);
    }

    @Override
    public String toString() {
        return "Author{" +
               "name='" + name + '\'' +
               '}';
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Author) obj;
        return Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
