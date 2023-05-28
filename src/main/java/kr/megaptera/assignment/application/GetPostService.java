package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.exceptions.NotFoundException;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetPostService {

    private final PostRepository postRepository;

    public GetPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public ResponseEntity<PostDto> getPostById(int postId) {
        Post post = postRepository.findById(Integer.toString(postId))
                .orElseThrow(NotFoundException::new);
        PostDto postDto = new PostDto(post);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }
}
