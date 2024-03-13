package kr.megaptera.assignment;

import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    void postList() {
        
        assertThat(postRepository.getPostList()).hasSize(0);
    }
}
