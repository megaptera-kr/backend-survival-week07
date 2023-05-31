package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domains.dto.CommentDto;
import kr.megaptera.assignment.domains.dto.CommentUpdateDto;
import kr.megaptera.assignment.domains.model.Comment;
import kr.megaptera.assignment.domains.model.CommentId;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateCommentService {

    private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto updateComment(String id, String postId, CommentUpdateDto commentUpdateDto) {
        Optional<Comment> check = commentRepository.findById(CommentId.of(id));
        if (check.isEmpty())
            throw new CommentNotFound();
        Comment comment = check.get();
        comment.updateComment(commentUpdateDto);
        commentRepository.save(comment);
        return new CommentDto(comment);
    }
}
