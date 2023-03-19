package kr.megaptera.assignment.models;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CommentTest {

    @Test
    void createComment() {
        Post post = new Post(new PostId("ID0701"), "제목", "작성자", "내용");
        Comment comment = new Comment("댓글작성자", "댓굴내용", post);

        assertThat(comment.author.equals("댓글작성자"));
        assertThat(comment.content.equals("댓굴내용"));
        assertThat(comment.post.id.equals("ID0701"));
    }

    @Test
    void updateComment() {
        Post post = new Post(new PostId("ID0701"), "제목", "작성자", "내용");
        Comment comment = new Comment("댓글작성자", "댓굴내용", post);

        comment.update("새로운내용");

        assertThat(comment.content.equals("새로운내용"));
    }

}
