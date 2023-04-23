package kr.megaptera.assignment.applications.comments;

import kr.megaptera.assignment.dtos.comments.CommentReadDto;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCommentsService {
    private final CommentRepository commentRepository;

    @Autowired
    public GetCommentsService(
            CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentReadDto> execute(String postId) {
        var models = commentRepository.findByPostId(postId);
        var dtos = models.stream().map(model -> new CommentReadDto(model)).toList();
        return dtos;
    }
}
