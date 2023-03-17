package kr.megaptera.assignment.applications.post;

import jakarta.transaction.*;
import kr.megaptera.assignment.dtos.post.*;
import kr.megaptera.assignment.models.post.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@Transactional
public class GetPostDtosService {

    private PostRepository postRepository;

    public GetPostDtosService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDTO> getPostDtos() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostDTO::new).toList();
    }
}
