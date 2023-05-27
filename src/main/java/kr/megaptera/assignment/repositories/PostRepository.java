package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.domains.model.Post;
import kr.megaptera.assignment.domains.model.PostId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, PostId> {
    // TODO: JPA 이용해서 과제를 완성해 주세요.
}
