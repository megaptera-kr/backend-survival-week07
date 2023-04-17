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
class CreateCommentServiceTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CreateCommentService createCommentService;

    @Test
    @DisplayName("CREATE COMMENT")
    void createComment() {
        String postId = "post_001";

        CommentCreateDTO commentCreateDTO = new CommentCreateDTO(
                "jyh",
                "pi ka chu!!"
        );

        postRepository.save(
                new Post(
                        postId,
                        Title.of("test"),
                        Author.of("pikachu"),
                        Content.of("No! GGO BUK!!")
                )
        );

        createCommentService.create(postId, commentCreateDTO);

        Post post = postRepository.findById(postId).get();

        assertThat(post.list()).hasSize(1);
    }

}