package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetCommentsService {

    private final CommentRepository commentRepository;

    public GetCommentsService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public ResponseEntity<List<CommentDto>> getCommentByPostId(int postId) {
        List<Comment> commentList = commentRepository.findAllByPostId(PostId.of(postId));

        List<CommentDto> commentDtoList = commentList.stream().map(CommentDto::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }
}
