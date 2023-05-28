package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {

}
