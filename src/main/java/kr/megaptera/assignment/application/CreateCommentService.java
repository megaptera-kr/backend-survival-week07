package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domains.dto.CommentCreateDto;
import kr.megaptera.assignment.domains.dto.CommentDto;
import kr.megaptera.assignment.domains.model.Comment;
import kr.megaptera.assignment.domains.model.Post;
import kr.megaptera.assignment.domains.model.PostId;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateCommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CreateCommentService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public CommentDto createComment(String postId, CommentCreateDto commentCreateDto) {

        Optional<Post> check = postRepository.findById(PostId.of(postId));
        if (check.isEmpty()) {
            throw new PostNotFound();
        }
        Comment comment = new Comment(check.get(), commentCreateDto);
        commentRepository.save(comment);
        return new CommentDto(comment);
    }
}
