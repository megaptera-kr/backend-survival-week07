package kr.megaptera.assignment.repositories;

import com.github.f4b6a3.tsid.TsidCreator;
import jakarta.transaction.Transactional;
import kr.megaptera.assignment.models.comments.Comment;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("comment crud test")
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

    @Test
    @DisplayName("comments find by postId")
    public void findByPostIdTest(){
        var postId = TsidCreator.getTsid().toString();

        for (int i = 0; i < 10; i++) {
            var newComment = new Comment("comment_id" + i, postId, "author", "content");
            commentRepository.save(newComment);
        }
        commentRepository.save(new Comment("comment_id11", "other_post_id", "author", "content"));

        var comments = commentRepository.findByPostId(postId);
        assertThat(comments).hasSize(10);
    }
}