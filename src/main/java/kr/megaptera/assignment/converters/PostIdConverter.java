package kr.megaptera.assignment.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import kr.megaptera.assignment.vos.PostId;

import java.util.UUID;

@Converter(autoApply = true)
public class PostIdConverter implements AttributeConverter<PostId, UUID> {
    @Override
    public UUID convertToDatabaseColumn(PostId postId) {
        return postId.id();
    }

    @Override
    public PostId convertToEntityAttribute(UUID uuid) {
        return PostId.of(uuid);
    }
}
