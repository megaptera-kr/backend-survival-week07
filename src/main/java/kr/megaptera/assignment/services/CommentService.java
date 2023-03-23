package kr.megaptera.assignment.services;

import jakarta.transaction.Transactional;
import java.util.List;
import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.CommentUpdateDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {


    private PostRepository postRepository;


    private CommentRepository commentRepository;

    public CommentService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public List<CommentDto> getCommentList(String postId) {
        Post post = postRepository.findById(PostId.of(postId)).get();
        return commentRepository.findAllByPost(post).stream()
            .map(Comment::toDto).toList();
    }

    @Transactional
    public void saveComment(String postId, CommentCreateDto commentCreateDto) {
        Post post = postRepository.findById(PostId.of(postId)).get();

        Comment newComment = new Comment(commentCreateDto.getAuthor(),
            commentCreateDto.getContent(),
            post);

        commentRepository.save(newComment);
    }

    @Transactional
    public CommentDto updateComment(String commentId, CommentUpdateDto commentUpdateDto) {
        Comment updateComment = commentRepository.findById(CommentId.of(commentId)).get();
        updateComment.update(commentUpdateDto.getContent());

        commentRepository.save(updateComment);

        return updateComment.toDto();
    }

    @Transactional
    public CommentDto deleteComment(String commentId) {
        Comment deleteTarget = commentRepository.findById(CommentId.of(commentId)).get();

        commentRepository.delete(deleteTarget);

        return deleteTarget.toDto();
    }
}
