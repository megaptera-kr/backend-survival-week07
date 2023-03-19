package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostResponseDto;
import kr.megaptera.assignment.dtos.PostUpdateRequestDto;
import kr.megaptera.assignment.entities.PostEntity;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UpdatePostService {
  private final PostRepository postRepository;

  public UpdatePostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public PostResponseDto updatePost(String id, PostUpdateRequestDto postUpdateDto) {
    Optional<PostEntity> post = postRepository.findById(id);
    if (post.isEmpty()) {
      throw new PostNotFound();
    }

    post.get().update(
        postUpdateDto.getTitle(),
        postUpdateDto.getContent()
    );
    postRepository.save(post.get());

    return PostResponseDto.of(post.get());
  }
}
