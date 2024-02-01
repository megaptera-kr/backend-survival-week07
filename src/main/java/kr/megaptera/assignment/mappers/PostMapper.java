package kr.megaptera.assignment.mappers;

import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDetailDto;
import kr.megaptera.assignment.entities.PostEntity;
import kr.megaptera.assignment.vos.Author;
import kr.megaptera.assignment.vos.PostContent;

public class PostMapper {
    public static PostDetailDto postDetailDto(PostEntity entity) {
        return new PostDetailDto(
                entity.getId().id().toString(),
                entity.getTitle(),
                entity.getAuthor().name(),
                entity.getContent().plainText()
        );
    }

    public static PostEntity postEntity(PostCreateDto dto) {
        return PostEntity.create(
                dto.getTitle(),
                Author.of(dto.getAuthor()),
                PostContent.of(dto.getContent())
        );
    }
}
