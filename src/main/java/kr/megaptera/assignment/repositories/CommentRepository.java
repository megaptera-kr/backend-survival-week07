package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.domains.model.Comment;
import kr.megaptera.assignment.domains.model.CommentId;
import kr.megaptera.assignment.domains.model.PostId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, CommentId> {

    List<Comment> findByPostId(PostId postId);
}
