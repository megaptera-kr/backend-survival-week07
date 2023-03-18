package kr.megaptera.assignment.applications.post;

import jakarta.transaction.*;
import kr.megaptera.assignment.dtos.post.*;
import kr.megaptera.assignment.exceptions.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
@Transactional
public class GetPostDtoService {

    private PostRepository postRepository;

    public GetPostDtoService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDTO getPostDto(String id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);
        return new PostDTO(post);
    }
}
