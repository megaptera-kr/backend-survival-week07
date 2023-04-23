package kr.megaptera.assignment.applications;

import kr.megaptera.assignment.domains.*;
import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.exceptions.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional
public class UpdatePostService {
    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void updatePost(String id, PostUpdateDto postUpdateDto) {

        Post post = postRepository.findById(PostId.of(id)).orElseThrow(PostNotFound::new);

        post.update(postUpdateDto.getTitle(), postUpdateDto.getContent());

    }
}
