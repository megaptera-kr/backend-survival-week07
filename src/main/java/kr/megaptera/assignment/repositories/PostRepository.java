package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, PostId> {
    List<Post> findAll();

    //findbyId()는 위에처럼 상속받으면 기본으로 제공되는 기능임.
}
