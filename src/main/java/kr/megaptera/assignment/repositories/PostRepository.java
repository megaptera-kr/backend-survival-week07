package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.posts.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends CrudRepository<Post, UUID> {
    List<Post> findAll();
}
