package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CommentId implements Serializable {

    private String id;

    public static CommentId of(String id) {
        return new CommentId(id);
    }

    public static CommentId generate() {
        return new CommentId(TsidCreator.getTsid().toString());
    }

    @Override
    public String toString() {
        return id;
    }

}
