package kr.megaptera.assignment.application;


import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeletePostService {
    private PostRepository postRepository;

    public DeletePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public void deletePost(String id) {
        postRepository.deleteById(Long.parseLong(id));
    }
}
