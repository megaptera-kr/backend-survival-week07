package kr.megaptera.assignment.applications;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.CommentUpdateDto;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UpdateCommentService {
    private CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void updateComment(String id, CommentUpdateDto commentUpdateDto) {
        Comment byId = commentRepository.findById(CommentId.of(id)).orElseThrow(CommentNotFound::new);
        byId.setContent(commentUpdateDto.getContent());
    }

    public void updateComment(String id, String postId,
                              CommentUpdateDto commentUpdatedDto) {
        Comment comment = commentRepository
                .findByIdAndPostId(CommentId.of(id), PostId.of(postId))
                .orElseThrow(CommentNotFound::new);

        comment.setContent(commentUpdatedDto.getContent());
    }
}
