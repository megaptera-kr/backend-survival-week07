package kr.megaptera.assignment.vos;

import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class PostId implements Serializable {
    @Column(name = "post_id")
    private UUID id;

    protected PostId() {

    }

    protected PostId(
            UUID id
    ) {
        this.id = id;
    }

    public static PostId of(UUID value) {
        return new PostId(value);
    }

    public static PostId generate() {
        UUID uuid = UlidCreator.getMonotonicUlid().toUuid();
        return new PostId(uuid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostId postId = (PostId) o;
        return id.equals(postId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id == null ? "<null>" : id.toString();
    }

    @Column(name = "post_id")
    public UUID id() {
        return id;
    }

}
