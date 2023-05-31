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
public class DeletePostService {

    private final PostRepository postRepository;

    public DeletePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public ResponseEntity<PostDto> deletePost(String postId) {
        Post post = postRepository.findById(new PostId(postId))
                .orElseThrow(NotFoundException::new);
        postRepository.delete(post);
        PostDto postDto = new PostDto(post);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }
}
