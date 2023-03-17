package kr.megaptera.assignment.models.post;

import com.github.f4b6a3.tsid.*;
import jakarta.persistence.*;

import java.util.*;

@Embeddable
public class PostId {
    @Column(name = "id")
    private String postId;

    public PostId() {
    }

    public PostId(String postId) {
        this.postId = postId;
    }

    public static PostId of(String postId) {
        return new PostId(postId);
    }

    public static PostId generate() {
        return new PostId(TsidCreator.getTsid().toString());
    }

    @Override
    public String toString() {
        return postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostId postId1 = (PostId) o;
        return Objects.equals(postId, postId1.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }
}
