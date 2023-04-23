package kr.megaptera.assignment.applications.posts;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.posts.PostReadDto;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.models.posts.Post;
import kr.megaptera.assignment.models.posts.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetPostService {
    @Autowired
    private final PostRepository postRepository;

    @Autowired
    public GetPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostReadDto execute(String id) throws PostNotFoundException {
        var optionalModel = postRepository.findById(id);
        if(optionalModel.isEmpty()){
            throw new PostNotFoundException();
        }

        var model = optionalModel.get();
        return new PostReadDto(model);
    }
}