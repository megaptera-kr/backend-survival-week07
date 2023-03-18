package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;
import jakarta.persistence.Embeddable;

@Embeddable
public class CommentId {

    String id;

    public CommentId() {
    }

    public CommentId(String id) {
        this.id = id;
    }

    public static CommentId generate() {
        return new CommentId(TsidCreator.getTsid().toString());
    }

    public static CommentId of(String commentId) {
        return new CommentId((commentId));
    }

    @Override
    public String toString() {
        return id;
    }
}
