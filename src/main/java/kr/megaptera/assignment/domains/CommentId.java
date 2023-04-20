package kr.megaptera.assignment.domains;

import com.github.f4b6a3.tsid.*;
import jakarta.persistence.*;

import java.util.*;

@Embeddable
public class CommentId {

    @Column(name = "comment_id")
    private String id;

    private CommentId() {
    }

    public CommentId(String id) {
        this.id = id;
    }

    public static CommentId generate() {
        return new CommentId(TsidCreator.getTsid().toString());
    }

    public static CommentId of(String id) {
        return new CommentId(id);
    }

    @Override
    public boolean equals(Object o) {
        CommentId otherCommentId = (CommentId) o;

        return Objects.equals(this, ((CommentId) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id;
    }

    public String getId() {
        return id;
    }
}
