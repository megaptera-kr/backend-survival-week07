package kr.megaptera.assignment.applications.comment;

import jakarta.transaction.*;
import kr.megaptera.assignment.dtos.comment.*;
import kr.megaptera.assignment.exceptions.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
@Transactional
public class UpdateCommentService {

    private final PostRepository postRepository;

    public UpdateCommentService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void update(String id,
                       String postId,
                       CommentUpdateDTO commentUpdateDTO) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFound::new);
        post.updateComment(id, commentUpdateDTO);
    }
}

