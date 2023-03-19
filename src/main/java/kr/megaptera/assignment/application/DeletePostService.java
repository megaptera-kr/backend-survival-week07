package kr.megaptera.assignment.application;

import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeletePostService {
  private final PostRepository postRepository;

  public DeletePostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public void deletePost(String id) {
    postRepository.deleteById(id);
  }
}
