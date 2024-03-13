package kr.megaptera.assignment.daos;

import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Component;

@Component
public class CommentDao {
    private final CommentRepository commentRepository;

    public CommentDao(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
