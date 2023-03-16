package kr.megaptera.assignment.application;

import java.util.List;
import kr.megaptera.assignment.dtos.CommentResponse;
import kr.megaptera.assignment.dtos.CreateCommentRequest;
import kr.megaptera.assignment.dtos.UpdateCommentRequest;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Author;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.Content;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public List<CommentResponse> getComments(String postId) {
        List<Comment> comments = commentRepository.findByPostId(Long.parseLong(postId));

        return comments.stream().map(CommentResponse::new).toList();
    }

    @Transactional
    public CommentResponse createComment(String postId, CreateCommentRequest request) {
        Post post = postRepository.findById(Long.parseLong(postId)).orElseThrow(PostNotFound::new);
        Comment comment = new Comment(post, Author.of(request.getAuthor()),
                Content.of(request.getContent()));
        Comment saved = commentRepository.save(comment);

        return new CommentResponse(saved);
    }

    @Transactional
    public void updateComment(String id, String postId, UpdateCommentRequest request) {
        Comment comment = commentRepository.findByIdAndPostId(Long.parseLong(id),
                        Long.parseLong(postId))
                .orElseThrow(CommentNotFound::new);

        comment.updateContent(request.getContent());
    }

    @Transactional
    public void deleteComment(String id, String postId) {
        Comment comment = commentRepository.findByIdAndPostId(Long.parseLong(id),
                        Long.parseLong(postId))
                .orElseThrow(CommentNotFound::new);

        commentRepository.delete(comment);
    }
}
