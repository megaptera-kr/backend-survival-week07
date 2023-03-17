package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.comment.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface CommentRepository extends JpaRepository<Comment, CommentId> {
    List<Comment> findAll(String postId);

    Comment save(Comment comment);
}
