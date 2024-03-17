package kr.megaptera.assignment.daos;

import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;

import java.util.List;

public interface PostDao {
    List<Post> findAll();

    Post find(PostId id);

    void save(Post post);

    void delete(PostId id);
}