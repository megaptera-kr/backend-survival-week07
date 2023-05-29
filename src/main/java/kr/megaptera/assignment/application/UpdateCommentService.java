package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.request.RqUpdateCommentDto;
import kr.megaptera.assignment.exceptions.NotFoundException;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.Content;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {

    private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public ResponseEntity<CommentDto> updateComment(RqUpdateCommentDto dto, String postId, String commentId) {
        Comment comment = commentRepository.findById(CommentId.of(commentId))
                .orElseThrow(NotFoundException::new);
        if (!comment.isEqualPostId(PostId.of(postId))) {
            throw new NotFoundException();
        }
        comment.update(Content.of(dto.getContent()));
        Comment updateComment = commentRepository.save(comment);
        CommentDto commentDto = new CommentDto(comment);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }
}
