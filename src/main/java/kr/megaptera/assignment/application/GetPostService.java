package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domains.dto.PostDto;
import kr.megaptera.assignment.domains.model.Post;
import kr.megaptera.assignment.domains.model.PostId;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetPostService {

    private final PostRepository postRepository;

    public GetPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto getPost(String id) {
        Optional<Post> check = postRepository.findById(PostId.of(id));
        if (check.isEmpty())
            throw new PostNotFound();
        return new PostDto(check.get());
    }
}
