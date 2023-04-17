package kr.megaptera.assignment.applications.post;

import jakarta.transaction.*;
import kr.megaptera.assignment.dtos.post.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CreatePostDtoServiceTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CreatePostDtoService createPostDtoService;

    @Test
    @DisplayName("Create Post")
    void createPost() {
        PostCreateDTO postCreateDTO = new PostCreateDTO(
                "test",
                "jyh",
                "pi ka chu!!"
        );

        PostDTO postDTO = createPostDtoService.create(postCreateDTO);

        assertThat(postDTO.getTitle().equals("test"));
        assertThat(postDTO.getAuthor().equals("jyh"));
        assertThat(postDTO.getContent().equals("pi ka chu!!"));
    }

}