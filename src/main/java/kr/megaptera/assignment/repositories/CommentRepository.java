package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, CommentId> {
    List<Comment> findAllByPostId(PostId postId);

    Optional<Comment> findByIdAndPostId(CommentId commentId, PostId postId);
}
