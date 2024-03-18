package kr.megaptera.assignment.services;

import kr.megaptera.assignment.repositories.PostRepository;
import kr.megaptera.assignment.vos.PostId;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeletePostService {
    private final PostRepository postRepository;

    public DeletePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public void delete(String id) {
        this.postRepository.deleteById(PostId.of(UUID.fromString(id)));
    }
}
