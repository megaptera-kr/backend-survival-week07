package kr.megaptera.assignment.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import kr.megaptera.assignment.vos.Author;

@Converter(autoApply = true)
public class AuthorConverter implements AttributeConverter<Author, String> {
    @Override
    public String convertToDatabaseColumn(Author author) {
        return author.name();
    }

    @Override
    public Author convertToEntityAttribute(String s) {
        return Author.of(s);
    }
}
