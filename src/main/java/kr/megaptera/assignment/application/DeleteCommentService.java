package kr.megaptera.assignment.application;

import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteCommentService {
    private final CommentRepository commentRepository;

    public DeleteCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public void deleteComment(String id, String postId) {
        Comment comment = commentRepository.findByIdAndPostId(Long.parseLong(id), Long.parseLong(postId));
        commentRepository.delete(comment);
    }
}
