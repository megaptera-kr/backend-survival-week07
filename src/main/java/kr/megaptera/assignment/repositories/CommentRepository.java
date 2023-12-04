package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Comment findByIdAndPostId(Long commentId, Long postId);

    List<Comment> findAllByPostId(long postId);
}
