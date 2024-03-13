package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, String> {
}
