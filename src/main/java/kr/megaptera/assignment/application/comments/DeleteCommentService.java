package kr.megaptera.assignment.application.comments;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.posts.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class DeleteCommentService {
    private final PostRepository postRepository;

    public DeleteCommentService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void deleteComment(String postId, String commentId) {
        Post post = this.postRepository.findById(UUID.fromString(postId)).orElseThrow(PostNotFound::new);

        post.deleteComment(UUID.fromString(commentId));
    }
}
