package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostsService {

    private final PostRepository postRepository;

    public GetPostsService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public ResponseEntity<List<PostDto>> getPostList() {
        List<Post> postList = postRepository.findAll();

        List<PostDto> postDtoList = postList.stream()
                .map(PostDto::new)
                .toList();

        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }
}
