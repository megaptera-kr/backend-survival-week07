package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;
import jakarta.persistence.Embeddable;

@Embeddable
public class PostId {

    String id;

    public PostId() {
    }

    public PostId(String id) {
        this.id = id;
    }

    public static PostId generate() {
        return new PostId(TsidCreator.getTsid().toString());
    }

    public static PostId of(String id) {
        return new PostId(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
