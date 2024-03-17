package kr.megaptera.assignment.daos;

import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;

import java.util.List;

public interface CommentDao {
    List<Comment> findAll(PostId postId);

    Comment find(CommentId id, PostId postId);

    void save(Comment comment);

    void delete(CommentId id);
}