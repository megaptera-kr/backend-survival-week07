package kr.megaptera.assignment.applications;

import kr.megaptera.assignment.domains.*;
import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.exceptions.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UpdatePostService {
    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void updatePost(String id, PostUpdateDto postUpdateDto) {
        Optional<Post> post = postRepository.findById(PostId.of(id));
        if (post.isPresent()) {
            Post _post = post.get();
            _post.update(postUpdateDto.getTitle(), postUpdateDto.getContent());
            postRepository.save(_post);
        } else throw new PostNotFound();
    }
}
