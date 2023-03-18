package kr.megaptera.assignment.applications.post;

import jakarta.transaction.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class DeletePostDtoServiceTest {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private DeletePostDtoService deletePostDtoService;

    @Test
    @DisplayName("DELETE 게시글 삭제")
    void delete() {
        String postId = "ID_001";

        Post targetPost = new Post(
                postId,
                Title.of("title"),
                Author.of("jyh"),
                Content.of("pi ka chu!!")
        );

        postRepository.save(targetPost);

        assertThat(postRepository.findAll()).hasSize(1);

        deletePostDtoService.delete(postId);

        assertThat(postRepository.findAll()).hasSize(0);
    }
}