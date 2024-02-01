package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.entities.CommentEntity;
import kr.megaptera.assignment.vos.CommentId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, CommentId> {
}
