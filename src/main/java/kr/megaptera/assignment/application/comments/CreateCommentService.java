package kr.megaptera.assignment.application.comments;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.comments.CreateCommentDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.columns.Author;
import kr.megaptera.assignment.models.columns.Content;
import kr.megaptera.assignment.models.posts.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class CreateCommentService {
    private final PostRepository postRepository;

    public CreateCommentService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createComment(String postId, CreateCommentDto createCommentDto) {
        Post post = this.postRepository.findById(UUID.fromString(postId)).orElseThrow(PostNotFound::new);

        post.addComment(
                new Author(createCommentDto.getAuthor()),
                new Content(createCommentDto.getContent())
        );
    }
}
