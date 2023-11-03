package kr.megaptera.assignment.application.posts;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.posts.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class GetPostService {
    private final PostRepository postRepository;

    public GetPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto getPost(String id) {
        return PostDto.of(this.postRepository.findById(UUID.fromString(id)).orElseThrow(PostNotFound::new));
    }
}
