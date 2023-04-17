package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.*;
import org.springframework.data.jpa.repository.*;

public interface CommentRepository extends JpaRepository<Comment, String> {

}
