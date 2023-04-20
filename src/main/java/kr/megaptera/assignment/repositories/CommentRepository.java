package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.domains.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface CommentRepository extends JpaRepository<Comment, CommentId> {

    List<Comment> findAllById(PostId postId);

    Optional<Comment> findByIdAndPostId(String id, String postId);

}
