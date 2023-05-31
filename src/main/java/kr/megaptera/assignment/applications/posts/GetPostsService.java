package kr.megaptera.assignment.applications.posts;

import kr.megaptera.assignment.dtos.posts.PostReadDto;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class GetPostsService {
    private final PostRepository repository;

    @Autowired
    public GetPostsService(PostRepository postRepository) {
        repository = postRepository;
    }

    public List<PostReadDto> execute() {
        var models = repository.findAll();
        var dtos = StreamSupport.stream(models.spliterator(), false).map(post -> new PostReadDto(post)).toList();
        return dtos;
    }
}
