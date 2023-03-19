package kr.megaptera.assignment.repositories;
 
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, CommentId> {
    List<Comment> findAllByPostId(PostId postId);
    Optional<Comment> findByIdAndPostId(CommentId commentId, PostId postId); 
}
