package kr.megaptera.assignment.applications;

import kr.megaptera.assignment.domains.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
public class DeletePostService {
    private final PostRepository postRepository;

    public DeletePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void deletePost(String id) {
        postRepository.deleteById(PostId.of(id));
    }
}
