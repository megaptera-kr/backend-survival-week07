package kr.megaptera.assignment.mappers;

import kr.megaptera.assignment.dtos.CommentDetailDto;
import kr.megaptera.assignment.entities.CommentEntity;

public class CommentMapper {
    public static CommentDetailDto commentDetailDto(CommentEntity entity) {
        return new CommentDetailDto(
                entity.getCommentId().toString(),
                entity.getAuthor().name(),
                entity.getContent()
        );
    }
}
