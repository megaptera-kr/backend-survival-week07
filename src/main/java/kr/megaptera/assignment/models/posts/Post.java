package kr.megaptera.assignment.models.posts;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Embedded
    private MultilineText content;

    @Override
    public boolean equals(Object other) {
        return id.equals(((Post) other).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
