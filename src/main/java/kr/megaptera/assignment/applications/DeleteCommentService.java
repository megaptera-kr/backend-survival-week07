package kr.megaptera.assignment.applications;

import kr.megaptera.assignment.domains.*;
import kr.megaptera.assignment.exceptions.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class DeleteCommentService {
    private final CommentRepository commentRepository;

    public DeleteCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void delete(String id, String postId) {

        Optional<Comment> comment = commentRepository.findByIdAndPostId(id, postId);
        if (comment.isPresent()) {
            Comment _comment = comment.get();
            commentRepository.delete(_comment);
        } else throw new CommentNotFound();
    }
}
