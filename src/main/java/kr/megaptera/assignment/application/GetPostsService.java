package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domains.dto.PostDto;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostsService {

    private final PostRepository postRepository;

    public GetPostsService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> getPosts() {
        return postRepository.findAll()
                .stream().map(PostDto::new).toList();
    }
}
