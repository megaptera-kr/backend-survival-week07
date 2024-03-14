package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, String> {
    // TODO: JPA 이용해서 과제를 완성해 주세요.
    List<Post> getPostList();

    Post getpost(String id);
}
