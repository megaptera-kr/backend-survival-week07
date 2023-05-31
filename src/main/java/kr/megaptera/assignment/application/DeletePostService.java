package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domains.dto.PostDto;
import kr.megaptera.assignment.domains.model.Post;
import kr.megaptera.assignment.domains.model.PostId;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeletePostService {

    private final PostRepository postRepository;

    public DeletePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto deletePost(String id) {
        Optional<Post> check = postRepository.findById(PostId.of(id));
        if (check.isEmpty())
            throw new PostNotFound();

        Post post = check.get();
        postRepository.delete(post);
        return new PostDto(post);
    }
}
