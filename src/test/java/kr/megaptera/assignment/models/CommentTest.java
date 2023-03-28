package kr.megaptera.assignment.models;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommentTest {

    @Test
    @DisplayName("댓글 생성 테스트")
    void createComment() {
        Post post = new Post(new PostId("ID0701"), "제목", "작성자", "내용");
        Comment comment = new Comment("댓글작성자", "댓굴내용", post);

        assertThat(comment.author).isEqualTo("댓글작성자");
        assertThat(comment.content).isEqualTo("댓굴내용");
        assertThat(comment.post.id.toString()).isEqualTo("ID0701");
    }

    @Test
    @DisplayName("댓글 수정 테스트")
    void updateComment() {
        Post post = new Post(new PostId("ID0701"), "제목", "작성자", "내용");
        Comment comment = new Comment("댓글작성자", "댓굴내용", post);

        comment.update("새로운내용");

        assertThat(comment.content).isEqualTo("새로운내용");
    }

}
