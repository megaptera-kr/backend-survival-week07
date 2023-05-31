package kr.megaptera.assignment.applications.posts;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.posts.PostReadDto;
import kr.megaptera.assignment.dtos.posts.PostUpdateDto;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.models.posts.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {
    private final PostRepository postRepository;

    @Autowired
    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostReadDto execute(String postId, PostUpdateDto reqBody) throws PostNotFoundException {

        var optionalModel = postRepository.findById(postId);
        if(optionalModel.isEmpty()){
            throw new PostNotFoundException();
        }

        var model = optionalModel.get();
        model.setTitle(reqBody.getTitle());
        model.getContent().appendText(reqBody.getContent());

        update(model);

        return new PostReadDto(model);
    }

    @Transactional
    public void update(Post post){
        postRepository.save(post);
    }
}
