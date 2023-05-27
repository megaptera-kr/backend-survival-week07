package kr.megaptera.assignment.domains.model;

import com.github.f4b6a3.tsid.TsidCreator;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PostId implements Serializable {

    @Column(insertable = false, updatable = false)
    private String id;

    public PostId() {
        this.id = generate();
    }

    public PostId(String id) {
        this.id = id;
    }

    public static PostId of(String id) {
        return new PostId(id);
    }

    public String getId() {
        return id;
    }

    public String generate() {
        return TsidCreator.getTsid().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostId postId)) return false;
        return Objects.equals(id, postId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
