package kr.megaptera.assignment.daos;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostDao {
    private final PostRepository postRepository;

    public PostDao(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> getPostList() {
        List<PostDto> postDtoList = postRepository.getPostList().stream()
                .map(post -> new PostDto(post))
                .collect(Collectors.toList());
        return postDtoList;
    }

    public PostDto getPost() {
        
    }
}
