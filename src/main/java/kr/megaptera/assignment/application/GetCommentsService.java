package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCommentsService {
    private final CommentRepository commentRepository;

    public GetCommentsService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentDto> getCommentDtos(String postId) {
        return commentRepository.findAllByPostId(Long.parseLong(postId)).stream().map((comment -> new CommentDto(comment))).toList();
    }
}
