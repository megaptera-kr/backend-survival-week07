package kr.megaptera.assignment.services;

import kr.megaptera.assignment.dtos.PostDetailDto;
import kr.megaptera.assignment.mappers.PostMapper;
import kr.megaptera.assignment.repositories.PostRepository;
import kr.megaptera.assignment.vos.PostId;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DetailPostService {
    private final PostRepository postRepository;

    public DetailPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDetailDto detail(String id) {
        return postRepository.findById(PostId.of(UUID.fromString(id)))
                .map(PostMapper::postDetailDto)
                .orElse(null);
    }
}
