package kr.megaptera.assignment.service;

import kr.megaptera.assignment.daos.CommentDao;
import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.stereotype.Service;

@Service
public class CommentUpdateService {
    private final CommentDao commentDao;

    public CommentUpdateService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void updateComment(CommentDto commentDto) {
        commentDao.updateComment(commentDto);
    }
}
