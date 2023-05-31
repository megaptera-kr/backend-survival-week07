package kr.megaptera.assignment.models.comments;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @Column
    private String id;
    @Column
    private String postId;
    @Column
    private String author;
    @Column
    private String content;

    @Override
    public boolean equals(Object other) {
        return id.equals(((Comment) other).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
