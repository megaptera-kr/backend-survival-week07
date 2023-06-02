package kr.megaptera.assignment.application;

import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletePostService {
    private final PostRepository postRepository;

    public DeletePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void deletePost(String id) {
        Post post = postRepository.findById(PostId.of(id))
            .orElseThrow(PostNotFound::new);

        postRepository.delete(post);
    }
}
