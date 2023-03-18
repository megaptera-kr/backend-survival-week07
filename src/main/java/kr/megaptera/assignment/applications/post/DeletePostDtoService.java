package kr.megaptera.assignment.applications.post;

import jakarta.transaction.*;
import kr.megaptera.assignment.exceptions.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
@Transactional
public class DeletePostDtoService {
    private PostRepository postRepository;

    public DeletePostDtoService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public void delete(String id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFound::new);

        postRepository.delete(post);
    }
}
