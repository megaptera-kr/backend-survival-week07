package kr.megaptera.assignment.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;

import java.io.Serializable;

@Embeddable
public class CommentId implements Serializable {
    @Column(name = "id")
    private String value;

    public CommentId() {
    }

    public CommentId(String value){
        this.value = value;
    }
    public static CommentId of(String id) {
        return new CommentId(id);
    }

    @Override
    public String toString() {
        return value;
    }
}
