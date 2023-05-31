package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domains.dto.CommentDto;
import kr.megaptera.assignment.domains.model.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCommentsService {

    private final CommentRepository commentRepository;

    public GetCommentsService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentDto> getComments(String postId) {
        return commentRepository.findByPostId(PostId.of(postId))
                .stream().map(CommentDto::new).toList();
    }
}
