package kr.megaptera.assignment.applications;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.PostId;
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
        List<Comment> allByPostId = commentRepository.findAllByPostId(PostId.of(postId));
        return allByPostId.stream()
                .map(CommentDto::new)
                .toList();
    }
}
