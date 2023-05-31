package kr.megaptera.assignment.application;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeletePostService {
    private final PostRepository postRepository;

    public DeletePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void delete(String postId) {
        Post post = postRepository.findById(PostId.of(postId)).orElseThrow(PostNotFound::new);

        postRepository.delete(post);
    }
}
