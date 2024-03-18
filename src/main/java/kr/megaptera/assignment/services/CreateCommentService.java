package kr.megaptera.assignment.services;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.repositories.PostRepository;
import kr.megaptera.assignment.vos.Author;
import kr.megaptera.assignment.vos.PostId;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateCommentService {
    private final PostRepository postRepository;

    public CreateCommentService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void create(String postId, CommentCreateDto dto) {
        postRepository.findById(PostId.of(UUID.fromString(postId)))
                .ifPresent(entity -> {
                    entity.addComment(
                            Author.of(dto.getAuthor()),
                            dto.getContent()
                    );
                    postRepository.save(entity);
                });
    }
}
