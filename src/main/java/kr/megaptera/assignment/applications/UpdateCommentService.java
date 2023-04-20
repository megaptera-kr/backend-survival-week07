package kr.megaptera.assignment.applications;

import kr.megaptera.assignment.domains.*;
import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UpdateCommentService {
    private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void updateCommentDto(String id, String postId, CommentUpdateDto commentUpdateDto) {
        Optional<Comment> comment = commentRepository.findByIdAndPostId(id, postId);
        if (comment.isPresent()) {
            Comment _comment = comment.get();
            _comment.update(commentUpdateDto.getContent());
            commentRepository.save(_comment);
        }

    }
}
