package kr.megaptera.assignment.applications.comment;

import jakarta.transaction.*;
import kr.megaptera.assignment.dtos.comment.*;
import kr.megaptera.assignment.models.comment.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@Transactional
public class GetCommentDtosService {

    private CommentRepository commentRepository;

    public GetCommentDtosService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentDTO> getCommentDtos(String postId) {
        List<Comment> comments = commentRepository.findAll(postId);
        return comments.stream().map(CommentDTO::new).toList();
    }
}
