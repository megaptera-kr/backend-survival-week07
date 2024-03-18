package kr.megaptera.assignment.vos;

import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class CommentId implements Serializable {
    @Column(name = "comment_id")
    private UUID id;

    public CommentId() {
    }

    protected CommentId(
            UUID id
    ) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public static CommentId of(UUID id) {
        return new CommentId(id);
    }

    public static CommentId generate() {
        UUID id = UlidCreator.getMonotonicUlid().toUuid();
        return new CommentId(id);
    }

    @Override
    public String toString() {
        return id == null ? "<null>" : id.toString();
    }

    @Column(name = "comment_id")
    public UUID id() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CommentId) obj;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
