package kr.megaptera.assignment.applications.comment;

import jakarta.transaction.*;
import kr.megaptera.assignment.exceptions.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
@Transactional
public class DeleteCommentService {

    private PostRepository postRepository;

    public DeleteCommentService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void delete(String id, String postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFound::new);

        post.removeComment(id);
    }
}
