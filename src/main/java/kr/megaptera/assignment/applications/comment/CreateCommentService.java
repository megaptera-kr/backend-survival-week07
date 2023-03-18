package kr.megaptera.assignment.applications.comment;

import jakarta.transaction.*;
import kr.megaptera.assignment.dtos.comment.*;
import kr.megaptera.assignment.exceptions.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
@Transactional
public class CreateCommentService {

    private PostRepository postRepository;

    public CreateCommentService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public CommentDTO create(String postId, CommentCreateDTO commentCreateDTO) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFound::new);
        Comment comment = post.addComment(commentCreateDTO);
        return new CommentDTO(comment);
    }
}
