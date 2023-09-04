package kr.megaptera.assignment.application;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.CommentUpdatedDto;
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

    public void updateComment(String id, String postId,
                              CommentUpdatedDto commentUpdatedDto) {
        Comment comment = commentRepository
            .findByIdAndPostId(CommentId.of(id), PostId.of(postId))
            .orElseThrow(CommentNotFound::new);

        comment.update(commentUpdatedDto.getContent());
    }
}
