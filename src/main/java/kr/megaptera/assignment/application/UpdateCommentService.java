package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.CommentUpdateDto;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UpdateCommentService {

    private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto updateComment(String id,
                                    String postId,
                                    CommentUpdateDto commentUpdateDto) {
        Optional<Comment> comment = commentRepository
                .findByIdAndPostId(CommentId.of(id), PostId.of(postId));

        if(comment.isEmpty())
            throw new CommentNotFound();

        comment.get().update(commentUpdateDto.getContent());

        return new CommentDto(comment.get());
    }
}
