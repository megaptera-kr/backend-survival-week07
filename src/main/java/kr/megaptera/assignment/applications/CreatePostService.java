package kr.megaptera.assignment.applications;

import kr.megaptera.assignment.domains.*;
import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
public class CreatePostService {
    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(PostCreateDto postCreateDto) {
        Post post = new Post(PostId.generate(), postCreateDto.getTitle(), postCreateDto.getAuthor(), postCreateDto.getContent());
        postRepository.save(post);
    }
}
