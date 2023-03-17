package kr.megaptera.assignment.applications.comment;

import jakarta.transaction.*;
import kr.megaptera.assignment.dtos.comment.*;
import kr.megaptera.assignment.exceptions.*;
import kr.megaptera.assignment.models.comment.*;
import kr.megaptera.assignment.models.post.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
@Transactional
public class UpdateCommentService {

    private CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void update(String commentId,
                       String postId,
                       CommentUpdateDTO commentUpdateDTO) {
        Comment comment = commentRepository.findById(CommentId.of(commentId))
                .filter(t -> t.postId().equals(PostId.of(postId)))
                .orElseThrow(() -> new CommentNotFound());
        comment.update(commentUpdateDTO.getContent());

        commentRepository.save(comment);
    }
}
