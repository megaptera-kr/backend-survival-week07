package kr.megaptera.assignment.application;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UpdatePostService {
    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void updatePost(String id, PostUpdateDto postUpdateDto) {
        Post post = postRepository.findById(PostId.of(id))
                .orElseThrow(PostNotFound::new);

        post.update(
                postUpdateDto.getTitle(),
                MultilineText.of(postUpdateDto.getContent())
        );
    }
}
