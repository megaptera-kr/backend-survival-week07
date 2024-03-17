package kr.megaptera.assignment.service;

import kr.megaptera.assignment.daos.CommentDao;
import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.stereotype.Service;

@Service
public class CommentDeleteService {
    private final CommentDao commentDao;

    public CommentDeleteService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void deleteComment(CommentDto commentDto) {
        commentDao.deleteComment(commentDto);
    }
}
