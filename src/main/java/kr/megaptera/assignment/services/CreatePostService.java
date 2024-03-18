package kr.megaptera.assignment.services;

import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.mappers.PostMapper;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void create(PostCreateDto dto) {
        postRepository.save(PostMapper.postEntity(dto));
    }
}
