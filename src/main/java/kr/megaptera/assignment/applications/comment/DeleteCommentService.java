package kr.megaptera.assignment.applications.comment;

import jakarta.transaction.*;
import kr.megaptera.assignment.exceptions.*;
import kr.megaptera.assignment.models.comment.*;
import kr.megaptera.assignment.models.post.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
@Transactional
public class DeleteCommentService {
    private CommentRepository commentRepository;

    public DeleteCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void delete(String commentId, String postId) {
        Comment comment = commentRepository.findById(CommentId.of(commentId))
                .filter(t -> t.postId().equals(PostId.of(postId)))
                .orElseThrow(() -> new CommentNotFound());

        commentRepository.delete(comment);
    }
}
