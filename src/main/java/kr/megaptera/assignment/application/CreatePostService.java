package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domains.dto.PostCreateDto;
import kr.megaptera.assignment.domains.dto.PostDto;
import kr.megaptera.assignment.domains.model.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {

    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto createPost(PostCreateDto postCreateDto) {
        Post post = new Post(postCreateDto);
        postRepository.save(new Post(postCreateDto));
        return new PostDto(post);
    }
}
