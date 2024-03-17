package kr.megaptera.assignment.daos;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CommentDao {
    private final CommentRepository commentRepository;

    public CommentDao(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentDto> getCommentList(CommentDto commentDto) {
        Iterable<Comment> comments = commentRepository.findAllById(Arrays.asList(commentDto.getPostId()));
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments) {
            commentDtos.add(new CommentDto(comment));
        }
        return commentDtos;
    }

    @Transactional
    public void insertComment(CommentDto commentDto) {
        commentRepository.save(new Comment(commentDto));
    }

    @Transactional
    public void updateComment(CommentDto commentDto) {
        commentRepository.save(new Comment(commentDto));
    }

    @Transactional
    public void deleteComment(CommentDto commentDto) {
        commentRepository.delete(new Comment(commentDto));
    }
}
