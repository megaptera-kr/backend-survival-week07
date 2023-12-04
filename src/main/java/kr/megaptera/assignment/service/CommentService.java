package kr.megaptera.assignment.service;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dto.*;
import kr.megaptera.assignment.exception.NotFoundException;
import kr.megaptera.assignment.model.Comment;
import kr.megaptera.assignment.model.Post;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<CommentResponse> listCommentsByPostId(String postId) {
        if (postId == null || postId.isEmpty()) {
            throw new NotFoundException("Post ID cannot be null or empty");
        }
        return commentRepository.findByPostId(postId).stream()
                .map(Comment::toCommentResponse)
                .collect(Collectors.toList());
    }
    @Transactional
    public void create(CommentCreateRequest request, String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        Comment comment = new Comment();
        comment.fromCreateRequest(request, post);

        commentRepository.save(comment);
    }
    @Transactional
    public void update(CommentUpdateRequest request, String id, String postId) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment with id " + id + " not found"));
        comment.fromUpdateRequest(request);
    }
    @Transactional
    public void delete(String id, String postId) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment with id " + id + " not found"));
        commentRepository.delete(comment);
    }

}
