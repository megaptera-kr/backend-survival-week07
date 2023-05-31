package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostService {
    private final PostRepository postRepository;

    public GetPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> getPostDtos() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostDto::new).toList();
    }

    public PostDto getPostDto(String postId) {
        Post post = postRepository.findById(PostId.of(postId)).orElseThrow(PostNotFound::new);
        return new PostDto(post);
    }
}
