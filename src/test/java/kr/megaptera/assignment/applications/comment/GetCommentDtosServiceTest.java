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
class GetCommentDtosServiceTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private GetCommentDtosService getCommentDtosService;

    @Test
    @DisplayName("GET CommentDtos")
    void list() {
        String postId = "PostID_001";
        Post post = new Post(
                postId,
                Title.of("title"),
                Author.of("author"),
                Content.of("content")
        );
        postRepository.save(post);

        Post targetPost = postRepository.findById(postId).get();

        assertThat(targetPost.list()).hasSize(0);

        targetPost.addComment(
                new CommentCreateDTO(
                        "author",
                        "content")
        );

        assertThat(targetPost.list()).hasSize(1);
    }

}