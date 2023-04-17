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
class DeleteCommentServiceTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private DeleteCommentService deleteCommentService;

    @Test
    @DisplayName("DELETE COMMENT")
    void deleteComment() {
        String postId = "post_001";
        Post post = new Post(
                postId,
                Title.of("title"),
                Author.of("author"),
                Content.of("content")
        );
        postRepository.save(post);

        Post targetPost = postRepository.findById(postId).get();

        targetPost.addComment(new CommentCreateDTO("author", "content"));

        assertThat(targetPost.list()).hasSize(1);

        String commentId = targetPost.list().get(0).id();

        deleteCommentService.delete(commentId, postId);

        assertThat(targetPost.list()).hasSize(0);
    }

}