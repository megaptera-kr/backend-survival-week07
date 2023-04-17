package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePostService {

    private final PostRepository postRepository;

    public PostDto updatePostDto(String id, PostDto postDto) {
        Post post = postRepository.findById(PostId.of(id)).orElseThrow();
        post.update(postDto.getTitle(), postDto.getContent());

        postRepository.save(post);
        return new PostDto(post);
    }

}
