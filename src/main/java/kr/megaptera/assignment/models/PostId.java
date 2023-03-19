package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PostId implements Serializable {
    @Column(name = "id")
    private String value;

    public PostId(String value) {
        this.value = value;
    }

    public PostId() {
    }

    public static PostId of(String value){
        return new PostId(value);
    }

    public static PostId generate(){
        return new PostId(TsidCreator.getTsid().toString());
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        PostId postId = (PostId) o;

        return Objects.equals(value, postId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
