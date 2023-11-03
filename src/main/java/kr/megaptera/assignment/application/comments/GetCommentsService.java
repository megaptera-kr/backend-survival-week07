package kr.megaptera.assignment.application.comments;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.comments.CommentDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.posts.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GetCommentsService {
    private final PostRepository postRepository;

    public GetCommentsService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<CommentDto> getComments(String postId) {
        Post post = this.postRepository.findById(UUID.fromString(postId)).orElseThrow(PostNotFound::new);

        return post.findAllComments().stream().map(CommentDto::of).toList();
    }
}
