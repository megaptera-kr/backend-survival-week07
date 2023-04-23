package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.comments.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, String> {
    List<Comment> findAll();

    List<Comment> findByPostId(String postId);

    Optional<Comment> findById(String id);

    Comment save(Comment post);

    void delete(Comment post);
}
