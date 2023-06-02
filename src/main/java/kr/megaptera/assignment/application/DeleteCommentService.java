package kr.megaptera.assignment.application;

import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {
    private final CommentRepository commentRepository;

    public DeleteCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void deleteComment(String id, String postId) {
        Comment comment = commentRepository
            .findByIdAndPostId(CommentId.of(id), PostId.of(postId))
            .orElseThrow(CommentNotFound::new);

        commentRepository.delete(comment);
    }
}
