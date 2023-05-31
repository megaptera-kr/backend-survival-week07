package kr.megaptera.assignment.applications.comments;

import com.github.f4b6a3.tsid.TsidCreator;
import kr.megaptera.assignment.dtos.comments.CommentCreateDto;
import kr.megaptera.assignment.dtos.comments.CommentReadDto;
import kr.megaptera.assignment.models.comments.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CreateCommentService(
            CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentReadDto execute(String postId, CommentCreateDto commentCreateDto) {
        var id = TsidCreator.getTsid().toString();
        var model = new Comment(
                id,
                postId,
                commentCreateDto.getAuthor(),
                commentCreateDto.getContent());

        commentRepository.save(model);

        var commentReadDto = new CommentReadDto(model);
        return commentReadDto;
    }
}
