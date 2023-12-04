package kr.megaptera.assignment.application;


import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdatePostService {
    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public void updatePost(String id, PostUpdateDto postUpdateDto) {
        Post post = postRepository.findById(Long.parseLong(id)).get();

        if (post == null) {
            throw new PostNotFound();
        }

        post.update(
                postUpdateDto.getTitle(),
                MultilineText.of(postUpdateDto.getContent())
        );
    }
}
