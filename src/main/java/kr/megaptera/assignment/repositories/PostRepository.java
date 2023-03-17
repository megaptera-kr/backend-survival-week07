package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.post.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface PostRepository extends JpaRepository<Post, PostId> {
    // TODO: JPA 이용해서 과제를 완성해 주세요.
    List<Post> findAll();

    Optional<Post> findById(PostId postId);

    Post save(Post post);

    void delete(Post post);

}
