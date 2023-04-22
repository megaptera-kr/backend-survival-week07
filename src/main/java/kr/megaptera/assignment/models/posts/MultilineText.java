package kr.megaptera.assignment.models.posts;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Embeddable
@Data
@NoArgsConstructor
    public class MultilineText {
        @Column(name = "content")
        private String[] lines;

        public MultilineText(String text) {
            appendText(text);
        }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();

        if (lines.length == 1) {
            stringBuilder.append(lines[0]);
        } else {
            Arrays.stream(lines).forEach(line -> stringBuilder.append(line + "\n"));
        }

        return stringBuilder.toString();
    }

    public void appendText(String text) {
        lines = text.split("\n");
    }
}

