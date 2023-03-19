package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, String> {
  // TODO: JPA 이용해서 과제를 완성해 주세요.
}
