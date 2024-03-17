package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {

//    // JDBC
//    private final JdbcCommentDao commentDao;
//
//    public CreateCommentService(JdbcCommentDao commentDao) {
//        this.commentDao = commentDao;
//    }
//
//    public void createComment(String postId,
//                              CommentCreateDto commentCreateDto) {
//        Comment comment = new Comment(
//                PostId.of(postId),
//                commentCreateDto.getAuthor(),
//                commentCreateDto.getContent()
//        );
//
//        commentDao.save(comment);
//    }

    // JPA
    private final CommentRepository commentRepository;

    public CreateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void createComment(String postId,
                              CommentCreateDto commentCreateDto) {
        Comment comment = new Comment(
                PostId.of(postId),
                commentCreateDto.getAuthor(),
                commentCreateDto.getContent()
        );

        commentRepository.save(comment);
    }
}