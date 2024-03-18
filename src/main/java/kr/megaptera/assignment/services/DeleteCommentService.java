package kr.megaptera.assignment.services;

import kr.megaptera.assignment.repositories.PostRepository;
import kr.megaptera.assignment.vos.CommentId;
import kr.megaptera.assignment.vos.PostId;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCommentService {
    private final PostRepository postRepository;

    public DeleteCommentService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void delete(String id, String postId) {
        postRepository.findById(PostId.of(UUID.fromString(postId)))
                .ifPresent(entity -> {
                    entity.removeComment(CommentId.of(UUID.fromString(id)));
                    postRepository.save(entity);
                });
    }
}
