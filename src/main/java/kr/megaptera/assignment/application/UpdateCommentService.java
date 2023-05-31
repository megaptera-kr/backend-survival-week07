package kr.megaptera.assignment.application;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.CommentUpdateDto;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UpdateCommentService {
    private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void update(String commentId, String postId, CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepository.findByIdAndPostId(CommentId.of(commentId), PostId.of(postId)).orElseThrow(CommentNotFound::new);

        comment.update(commentUpdateDto);
    }
}
