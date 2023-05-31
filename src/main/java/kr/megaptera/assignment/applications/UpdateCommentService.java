package kr.megaptera.assignment.applications;

import kr.megaptera.assignment.domains.*;
import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.exceptions.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional
public class UpdateCommentService {
    private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void updateCommentDto(String id, String postId, CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepository
                .findByIdAndPostId(CommentId.of(id), PostId.of(postId))
                .orElseThrow(CommentNotFound::new);

        comment.update(commentUpdateDto.getContent());

    }
}
