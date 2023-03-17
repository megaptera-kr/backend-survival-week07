package kr.megaptera.assignment.applications.post;

import kr.megaptera.assignment.dtos.post.*;
import kr.megaptera.assignment.models.post.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
public class CreatePostDtoService {
    private PostRepository postRepository;

    public CreatePostDtoService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDTO create(PostCreateDTO postCreateDTO) {
        Post saved = postRepository.save(
                new Post(
                        postCreateDTO.getTitle(),
                        postCreateDTO.getAuthor(),
                        MultilineText.of(postCreateDTO.getContent())
                )
        );
        return new PostDTO(saved);
    }
}
