package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentUpdatedDto;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateCommentService {
    private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public void updateComment(String id, String postId,
                              CommentUpdatedDto commentUpdatedDto) {
        Comment comment = commentRepository.findByIdAndPostId(Long.parseLong(id), Long.parseLong(postId));

        if (comment == null) {
            throw new CommentNotFound();
        }

        comment.update(MultilineText.of(commentUpdatedDto.getContent()));
    }
}
