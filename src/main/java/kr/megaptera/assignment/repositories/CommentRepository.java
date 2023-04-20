package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.domains.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface CommentRepository extends JpaRepository<Comment, CommentId> {

    List<Comment> findAllByPostId(PostId postId);

    @Query("SELECT c FROM Comment c WHERE c.id = :id AND c.postId = :postId")
    Optional<Comment> findByIdAndPostId(@Param("id") CommentId id, @Param("postId") PostId postId);


}
