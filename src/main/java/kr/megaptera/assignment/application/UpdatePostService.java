package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {
    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void update(String postId, PostUpdateDto postUpdateDto) {
        Post post = postRepository.findById(postId).get();

        post.update(postUpdateDto);
    }
}
