package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PostId implements Serializable {
    @Column(name = "post_id")
    private String value;

    public PostId() {
    }

    public PostId(String value) {
        this.value = value;
    }

    public static PostId of(String value) {
        return new PostId(value);
    }

    public static PostId generate() {
        return new PostId(TsidCreator.getTsid().toString());
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostId postId = (PostId) o;
        return Objects.equals(value, postId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
