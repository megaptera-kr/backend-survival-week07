package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePostService {

    private final PostRepository postRepository;

    public PostDto removePostDto(String id) {
        Post post = postRepository.findById(PostId.of(id)).orElseThrow();
        postRepository.delete(post);
        return new PostDto(post);
    }

}
