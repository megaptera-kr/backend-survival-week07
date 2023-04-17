package kr.megaptera.assignment.application;

import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeleteCommentService {
  private final CommentRepository commentRepository;

  public DeleteCommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public void deleteComment(String id, String postId) {
    commentRepository.deleteById(id);
  }
}
