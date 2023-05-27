package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    public List<CommentDto> getComments(String postId) {
        List<Comment> comments = commentRepository.findByPostId(PostId.of(postId));
        return comments.stream().map(comment -> new CommentDto(comment)).toList();
    }

    public void createComment(String postId, CommentDto dto) {
        Comment comment = new Comment(dto.getAuthor(), dto.getContent(), PostId.of(postId));
        commentRepository.save(comment);
    }

    public void updateComment(String id, String postId, CommentDto dto) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment == null) {
            throw new CommentNotFound();
        }
        comment.get().update(dto);
        commentRepository.save(comment.get());
    }

    public void deleteComment(String id, String postId) {
        commentRepository.deleteById(id);
    }
}
