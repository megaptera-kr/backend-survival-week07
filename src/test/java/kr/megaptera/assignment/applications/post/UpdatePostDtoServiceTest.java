package kr.megaptera.assignment.applications.post;

import jakarta.transaction.*;
import kr.megaptera.assignment.dtos.post.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UpdatePostDtoServiceTest {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UpdatePostDtoService updatePostDtoService;

    @Test
    @DisplayName("UPDATE POST")
    void update() {
        String postId = "ID_001";
        PostUpdateDTO postUpdateDTO = new PostUpdateDTO(
                "after_title",
                "after_content"
        );

        postRepository.save(
                new Post(
                        postId,
                        Title.of("before_title"),
                        Author.of("jyh"),
                        Content.of("before_content")
                )
        );

        Post post = postRepository.findById(postId).get();

        assertThat(post.title().toString().equals("before_title"));
        assertThat(post.content().toString().equals("before_content"));

        updatePostDtoService.update(postId, postUpdateDTO);

        assertThat(post.title().toString().equals("after_title"));
        assertThat(post.content().toString().equals("after_content"));
    }
}