package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.comments.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CommentRepository extends CrudRepository<Comment, UUID> {
}
