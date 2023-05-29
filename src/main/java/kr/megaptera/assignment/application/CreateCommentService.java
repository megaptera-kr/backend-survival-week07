package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.request.RqCreateCommentDto;
import kr.megaptera.assignment.models.Author;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.Content;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {

    private final CommentRepository commentRepository;

    public CreateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public ResponseEntity<CommentDto> createComment(RqCreateCommentDto dto, String postId) {
        Comment comment = new Comment(PostId.of(postId),
                Author.of(dto.getAuthor()),
                Content.of(dto.getContent()));

        Comment saveComment = commentRepository.save(comment);
        CommentDto commentDto = new CommentDto(comment);
        return new ResponseEntity<>(commentDto, HttpStatus.CREATED);
    }
}
