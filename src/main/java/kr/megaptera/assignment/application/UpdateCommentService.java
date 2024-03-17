package kr.megaptera.assignment.application;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.daos.JdbcCommentDao;
import kr.megaptera.assignment.dtos.CommentUpdateDto;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {

//    // JDBC
//    private final JdbcCommentDao commentDao;
//
//    public UpdateCommentService(JdbcCommentDao commentDao) {
//        this.commentDao = commentDao;
//    }
//
//    public void updateComment(String id, String postId,
//                              CommentUpdateDto commentUpdatedDto) {
//        Comment comment = commentDao.find(CommentId.of(id), PostId.of(postId));
//
//        comment.update(commentUpdatedDto.getContent());
//
//        commentDao.save(comment);
//    }

    // JPA
    private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public void updateComment(String id, String postId,
                              CommentUpdateDto commentUpdatedDto) {
        Comment comment = commentRepository.findByIdAndPostId(CommentId.of(id), PostId.of(postId))
                .orElseThrow(CommentNotFound::new);

        comment.update(commentUpdatedDto.getContent());
    }
}