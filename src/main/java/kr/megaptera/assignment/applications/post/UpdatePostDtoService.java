package kr.megaptera.assignment.applications.post;

import jakarta.transaction.*;
import kr.megaptera.assignment.dtos.post.*;
import kr.megaptera.assignment.exceptions.*;
import kr.megaptera.assignment.models.post.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
@Transactional
public class UpdatePostDtoService {
    private PostRepository postRepository;

    public UpdatePostDtoService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void update(String id, PostUpdateDTO postUpdateDTO) {
        Post post = postRepository.findById(PostId.of(id))
                .orElseThrow(() -> new PostNotFound());
        
        post.update(postUpdateDTO.getTitle(), postUpdateDTO.getContent());
        postRepository.save(post);
    }
}
