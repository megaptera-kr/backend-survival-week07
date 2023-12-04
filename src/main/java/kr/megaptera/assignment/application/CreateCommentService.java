package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateCommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CreateCommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public void createComment(String postId,
                              CommentCreateDto commentCreateDto) {
        Post post = postRepository.findById(Long.parseLong(postId)).get();

        if (post == null) {
            throw new PostNotFound();
        }

        Comment comment = new Comment(
                Long.parseLong(postId),
                commentCreateDto.getAuthor(),
                MultilineText.of(commentCreateDto.getContent())
        );

        commentRepository.save(comment);
    }
}
