package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentResponseDto;
import kr.megaptera.assignment.entities.CommentEntity;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GetCommentsService {
  private final CommentRepository commentRepository;

  public GetCommentsService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;


  }

  public List<CommentResponseDto> getComments(String postId) {
    List<CommentEntity> comments = commentRepository.findByPostId(postId);

    return comments.stream().map(CommentResponseDto::of).toList();
  }
}
