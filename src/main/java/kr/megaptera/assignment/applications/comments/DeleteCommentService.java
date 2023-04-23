package kr.megaptera.assignment.applications.comments;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.comments.CommentReadDto;
import kr.megaptera.assignment.exceptions.CommentNotFoundException;
import kr.megaptera.assignment.models.comments.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public DeleteCommentService(
            CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentReadDto execute(String commentId) throws CommentNotFoundException {
        var optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isEmpty()) {
            throw new CommentNotFoundException();
        }

        var comment = optionalComment.get();
        delete(comment);

        return new CommentReadDto(comment);
    }

    @Transactional
    private void delete(Comment comment){
        commentRepository.delete(comment);
    }
}
