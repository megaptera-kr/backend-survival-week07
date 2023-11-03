package kr.megaptera.assignment.application.posts;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.posts.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class DeletePostService {
    private final PostRepository postRepository;

    public DeletePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void deletePost(String id) {
        Post postToBeDeleted = this.postRepository.findById(UUID.fromString(id)).orElseThrow(PostNotFound::new);

        this.postRepository.delete(postToBeDeleted);
    }
}
