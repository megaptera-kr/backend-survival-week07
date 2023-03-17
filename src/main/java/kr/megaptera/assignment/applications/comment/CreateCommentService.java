package kr.megaptera.assignment.applications.comment;

import jakarta.transaction.*;
import kr.megaptera.assignment.dtos.comment.*;
import kr.megaptera.assignment.models.comment.*;
import kr.megaptera.assignment.models.post.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
@Transactional
public class CreateCommentService {

    private CommentRepository commentRepository;

    public CreateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDTO create(String postId,
                             CommentCreateDTO commentCreateDTO) {
        Comment comment = new Comment(
                PostId.of(postId),
                commentCreateDTO.getAuthor(),
                commentCreateDTO.getContent()
        );

        Comment saved = commentRepository.save(comment);

        return new CommentDTO(saved);
    }
}
