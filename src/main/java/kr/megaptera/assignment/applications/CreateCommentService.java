package kr.megaptera.assignment.applications;

import kr.megaptera.assignment.dtos.CreateCommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {
    private final CommentRepository commentRepository;

    public CreateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void createComment(String postId, CreateCommentDto createCommentDto) {
        createCommentDto.setPostId(postId);
        Comment comment = new Comment(createCommentDto);
        commentRepository.save(comment);
    }
}
