package kr.megaptera.assignment.models.posts;

import com.github.f4b6a3.tsid.TsidCreator;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostId {
    @Id
    @Column(name = "id")
    private String value;

    public static PostId gene() {
        var id = TsidCreator.getTsid().toString();
        return new PostId(id);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        PostId otherPostId = (PostId) other;

        return Objects.equals(value, otherPostId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
