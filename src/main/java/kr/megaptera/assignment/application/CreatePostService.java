package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void create(PostCreateDto postCreateDto) {
        Post post = new Post(
            postCreateDto.getAuthor(),
            postCreateDto.getTitle(),
            MultilineText.of(postCreateDto.getContent())
        );

        postRepository.save(post);
    }
}
