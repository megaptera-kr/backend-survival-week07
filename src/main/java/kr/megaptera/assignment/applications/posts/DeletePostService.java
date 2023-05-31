package kr.megaptera.assignment.applications.posts;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.posts.PostReadDto;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class DeletePostService {
    private final PostRepository postRepository;

    @Autowired
    public DeletePostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public PostReadDto execute(String postId) throws PostNotFoundException {
        var optionalModel = postRepository.findById(postId);
        if(optionalModel.isEmpty()){
            throw new PostNotFoundException();
        }

        var model = optionalModel.get();
        postRepository.delete(model);

        return new PostReadDto(model);
    }
}
