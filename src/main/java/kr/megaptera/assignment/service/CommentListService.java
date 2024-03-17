package kr.megaptera.assignment.service;

import kr.megaptera.assignment.daos.CommentDao;
import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentListService {
    private final CommentDao commentDao;

    public CommentListService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public List<CommentDto> getCommentList(CommentDto commentDto) {
        return commentDao.getCommentList(commentDto);
    }
}
