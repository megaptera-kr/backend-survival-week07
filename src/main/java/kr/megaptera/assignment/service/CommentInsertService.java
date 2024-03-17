package kr.megaptera.assignment.service;

import kr.megaptera.assignment.daos.CommentDao;
import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.stereotype.Service;

@Service
public class CommentInsertService {
    private final CommentDao commentDao;

    public CommentInsertService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void insertComment(CommentDto commentDto) {
        commentDao.insertComment(commentDto);
    }
}
