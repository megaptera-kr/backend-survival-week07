package kr.megaptera.assignment.repositories;

import java.util.List;
import java.util.Optional;
import kr.megaptera.assignment.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);

    Optional<Comment> findByIdAndPostId(Long id, Long postId);
}
