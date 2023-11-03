package kr.megaptera.assignment.application.comments;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.comments.UpdateCommentDto;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.models.comments.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class UpdateCommentService {
    private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void updateComment(String commentId, UpdateCommentDto updateCommentDto) {
        Comment comment = this.commentRepository.findById(UUID.fromString(commentId)).orElseThrow(CommentNotFound::new);

        comment.setContent(updateCommentDto.getContent());
    }
}
