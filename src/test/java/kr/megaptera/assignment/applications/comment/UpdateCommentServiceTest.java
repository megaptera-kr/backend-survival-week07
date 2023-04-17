package kr.megaptera.assignment.applications.comment;

import jakarta.transaction.*;
import kr.megaptera.assignment.dtos.comment.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UpdateCommentServiceTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UpdateCommentService updateCommentService;

    @Test
    @DisplayName("UPDATE COMMENT")
    void updateComment() {
        String postId = "PostID_001";
        Post post = new Post(
                postId,
                Title.of("title"),
                Author.of("author"),
                Content.of("content")
        );
        postRepository.save(post);

        CommentUpdateDTO commentUpdateDTO = new CommentUpdateDTO("after_content");

        Post targetPost = postRepository.findById(postId).get();

        targetPost.addComment(
                new CommentCreateDTO(
                        "author",
                        "before_content")
        );

        String commentId = targetPost.list().get(0).id();

        assertThat(targetPost
                .list()
                .get(0)
                .content()
                .toString()
                .equals("before_content")
        );

        updateCommentService.update(commentId, postId, commentUpdateDTO);

        assertThat(targetPost
                .list()
                .get(0)
                .content()
                .toString()
                .equals("after_content")
        );
    }
}