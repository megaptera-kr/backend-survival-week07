package kr.megaptera.assignment.application.posts;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.posts.UpdatePostDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.columns.Content;
import kr.megaptera.assignment.models.columns.Title;
import kr.megaptera.assignment.models.posts.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class UpdatePostService {
    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void updatePost(String id, UpdatePostDto updatePostDto) {
        Post postToBeUpdated = this.postRepository.findById(UUID.fromString(id)).orElseThrow(PostNotFound::new);

        postToBeUpdated.setTitle(new Title(updatePostDto.getTitle()));
        postToBeUpdated.setContent(new Content(updatePostDto.getContent()));
    }
}
