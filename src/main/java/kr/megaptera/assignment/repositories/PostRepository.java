package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.entities.PostEntity;
import kr.megaptera.assignment.vos.PostId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, PostId> {
    // TODO: JPA 이용해서 과제를 완성해 주세요.
}
