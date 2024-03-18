package kr.megaptera.assignment.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import kr.megaptera.assignment.vos.CommentId;

import java.util.UUID;

@Converter(autoApply = true)
public class CommentIdConverter implements AttributeConverter<CommentId, UUID> {
    @Override
    public UUID convertToDatabaseColumn(CommentId commentId) {
        return commentId.id();
    }

    @Override
    public CommentId convertToEntityAttribute(UUID uuid) {
        return CommentId.of(uuid);
    }
}
