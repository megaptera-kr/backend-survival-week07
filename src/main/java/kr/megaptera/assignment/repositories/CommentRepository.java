package kr.megaptera.assignment.repositories;

import com.github.f4b6a3.tsid.Tsid;
import kr.megaptera.assignment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findByPostId(String postId);
}
