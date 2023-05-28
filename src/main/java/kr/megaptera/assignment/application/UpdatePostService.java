package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.request.RqUpdatePostDto;
import kr.megaptera.assignment.exceptions.NotFoundException;
import kr.megaptera.assignment.models.Content;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.Title;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
 public class UpdatePostService {

    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public ResponseEntity<PostDto> updatePost(RqUpdatePostDto dto, int postId) {
        Post post = postRepository.findById(PostId.of(postId).toString())
                .orElseThrow(NotFoundException::new);
        post.update(Title.of(dto.getTitle()),
                Content.of(dto.getContent()));
        Post updatePost = postRepository.save(post);
        PostDto postDto = new PostDto(updatePost);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }
}
