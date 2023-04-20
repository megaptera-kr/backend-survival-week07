package kr.megaptera.assignment.applications;

import kr.megaptera.assignment.domains.*;
import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
public class CreateCommentService {
    private final CommentRepository commentRepository;

    public CreateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void createCommentDto(String postId, CommentCreateDto commentCreateDto) {
        Comment comment = new Comment(CommentId.generate(), commentCreateDto.getAuthor(), commentCreateDto.getContent(), PostId.of(postId));
        commentRepository.save(comment);
    }
}
