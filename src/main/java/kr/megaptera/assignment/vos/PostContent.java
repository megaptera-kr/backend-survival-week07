package kr.megaptera.assignment.vos;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public final class PostContent implements Serializable {
    private String plainText;

    protected PostContent() {
    }

    protected PostContent(
            String plainText
    ) {
        this.plainText = plainText;
    }

    public static PostContent of(String plainText) {
        return new PostContent(plainText);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostContent that = (PostContent) o;
        return Objects.equals(plainText, that.plainText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plainText);
    }

    @Override
    public String toString() {
        return "PostContent{" +
               "plainText='" + plainText + '\'' +
               '}';
    }

    public String plainText() {
        return plainText;
    }

}
