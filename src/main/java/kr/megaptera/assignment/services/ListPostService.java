package kr.megaptera.assignment.services;

import kr.megaptera.assignment.dtos.PostDetailDto;
import kr.megaptera.assignment.entities.PostEntity;
import kr.megaptera.assignment.mappers.PostMapper;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class ListPostService {
    private final PostRepository postRepository;

    public ListPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDetailDto> list() {
        return StreamSupport.stream(postRepository.findAll().spliterator(), false)
                .map(PostMapper::postDetailDto)
                .toList();
    }
}
