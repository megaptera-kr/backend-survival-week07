package kr.megaptera.assignment.services;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.repositories.PostRepository;
import kr.megaptera.assignment.vos.PostContent;
import kr.megaptera.assignment.vos.PostId;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdatePostService {
    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void update(String id, PostUpdateDto dto) {
        postRepository
                .findById(PostId.of(UUID.fromString(id)))
                .ifPresent(entity -> {
                    entity.updatePost(
                            dto.getTitle(),
                            PostContent.of(dto.getContent())
                    );
                    postRepository.save(entity);
                });
    }
}
