package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.posts.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, String> {
    List<Post> findAll();
    Optional<Post> findById(String id);
    Post save(Post person);
    void delete(Post person);
}
