package kr.megaptera.assignment.repositories;

import java.util.Optional;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {

    Optional<Post> findById(PostId id);
    // TODO: JPA 이용해서 과제를 완성해 주세요.
}
