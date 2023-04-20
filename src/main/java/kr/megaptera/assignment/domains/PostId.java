package kr.megaptera.assignment.domains;

import com.github.f4b6a3.tsid.*;
import jakarta.persistence.*;

import java.util.*;

@Embeddable
public class PostId {

    @Column(name = "post_id")
    private String value;

    private PostId() {
    }

    public PostId(String value) {
        this.value = value;
    }

    public static PostId generate() {
        return new PostId(TsidCreator.getTsid().toString());
    }

    public static PostId of(String value) {
        return new PostId(value);
    }

    @Override
    public boolean equals(Object o) {
        PostId otherPostId = (PostId) o;

        return Objects.equals(value, otherPostId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }
}
