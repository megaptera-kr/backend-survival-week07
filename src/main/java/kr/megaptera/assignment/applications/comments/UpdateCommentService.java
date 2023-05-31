package kr.megaptera.assignment.applications.comments;

import kr.megaptera.assignment.dtos.comments.CommentReadDto;
import kr.megaptera.assignment.dtos.comments.CommentUpdateDto;
import kr.megaptera.assignment.exceptions.CommentNotFoundException;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public UpdateCommentService(
            CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // TODO : (dh) postId는 쓸 곳이 없는데?!?
    public CommentReadDto execute(String commentId, String postId, CommentUpdateDto commentUpdateDto) throws CommentNotFoundException {
        var optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isEmpty()){
            throw new CommentNotFoundException();
        }

        var comment = optionalComment.get();
        comment.setContent(commentUpdateDto.getContent());

        commentRepository.save(comment);

        return new CommentReadDto(comment);
    }
}
