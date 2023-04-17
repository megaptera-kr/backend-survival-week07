package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentResponseDto;
import kr.megaptera.assignment.dtos.CommentUpdateRequestDto;
import kr.megaptera.assignment.entities.CommentEntity;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UpdateCommentService {
  private final CommentRepository commentRepository;

  public UpdateCommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public CommentResponseDto updateComment(String id, String postId,
                                          CommentUpdateRequestDto commentUpdatedDto) {
    Optional<CommentEntity> commentEntity = commentRepository.findById(id);
    if (commentEntity.isEmpty()) {
      throw new CommentNotFound();
    }
    commentEntity.get().update(commentUpdatedDto.getContent());

    return CommentResponseDto.of(commentEntity.get());
  }
}
