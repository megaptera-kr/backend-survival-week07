package kr.megaptera.assignment.exceptions;

public class CommentNotFound extends RuntimeException {
    public CommentNotFound() {
        super("댓글을 찾지 못했습니다.");
    }
}
