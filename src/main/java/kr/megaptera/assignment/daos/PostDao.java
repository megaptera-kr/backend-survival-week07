package kr.megaptera.assignment.daos;

import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Component;

@Component
public class PostDao {
    private final PostRepository postRepository;

    public PostDao(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
