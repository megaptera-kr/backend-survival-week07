package kr.megaptera.assignment;

import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostTest {

    @MockBean
    private PostRepository postRepository;

    @Test
    void postList() {
        assertThat(postRepository.getPostList()).hasSize(0);
    }

    @Test
    void postInsertTest() {

    }
}
