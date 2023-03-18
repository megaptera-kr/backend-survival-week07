package kr.megaptera.assignment.applications.post;

import kr.megaptera.assignment.dtos.post.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
public class CreatePostDtoService {
    private PostRepository postRepository;

    public CreatePostDtoService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public PostDTO create(PostCreateDTO postCreateDTO) {
        Post post = new Post(
                Title.of(postCreateDTO.getTitle()),
                Author.of(postCreateDTO.getAuthor()),
                Content.of(postCreateDTO.getContent()));
        postRepository.save(post);
        return new PostDTO(post);
    }
}
