package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostResponseDto;
import kr.megaptera.assignment.entities.PostEntity;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class GetPostService {
  private final PostRepository postRepository;

  public GetPostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public PostResponseDto getPostDetail(String id) {
    Optional<PostEntity> post = postRepository.findById(id);
    if (post.isEmpty()) {
      throw new PostNotFound();
      //
    }
    return PostResponseDto.of(post.get());
  }
}
