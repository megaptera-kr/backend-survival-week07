package kr.megaptera.assignment.repositories;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.models.comments.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void crudTest() {
        var newComment = new Comment("comment_id", "post_id", "author", "content");
        var savedComment = commentRepository.save(newComment);

        savedComment.setContent("updated content");
        var updatedComment = commentRepository.save(savedComment);
        assertThat(updatedComment).isNotNull();
        assertThat(updatedComment.getContent()).isEqualTo(savedComment.getContent());

        commentRepository.delete(updatedComment);

        var allComments = commentRepository.findAll();
        assertThat(allComments).doesNotHaveSameClassAs(updatedComment);
    }
}