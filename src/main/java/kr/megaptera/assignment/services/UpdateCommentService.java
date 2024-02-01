package kr.megaptera.assignment.services;

import kr.megaptera.assignment.dtos.CommentUpdateDto;
import kr.megaptera.assignment.repositories.PostRepository;
import kr.megaptera.assignment.vos.CommentId;
import kr.megaptera.assignment.vos.PostId;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateCommentService {
    private final PostRepository postRepository;

    public UpdateCommentService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void update(String id, String postId, CommentUpdateDto dto) {
        postRepository.findById(PostId.of(UUID.fromString(postId)))
                .ifPresent(entity -> {
                    entity.updateComment(
                            CommentId.of(UUID.fromString(id)),
                            dto.getContent()
                    );
                    postRepository.save(entity);
                });
    }
}
