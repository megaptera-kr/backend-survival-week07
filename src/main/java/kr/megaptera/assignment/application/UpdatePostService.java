package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domains.dto.PostDto;
import kr.megaptera.assignment.domains.dto.PostUpdateDto;
import kr.megaptera.assignment.domains.model.Post;
import kr.megaptera.assignment.domains.model.PostId;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdatePostService {

    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto updatePost(String id, PostUpdateDto postUpdateDto) {
        Optional<Post> check = postRepository.findById(PostId.of(id));

        if (check.isEmpty())
            throw new PostNotFound();

        Post post = check.get();

        post.updatePost(postUpdateDto);
        postRepository.save(post);
        return new PostDto(post);
    }
}
