package kr.megaptera.assignment.application.posts;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class GetPostsServiceTest {
    GetPostsService getPostsService;

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void setUp() {
        getPostsService = new GetPostsService(postRepository);
    }

    @Test
    @DisplayName("게시물 조회")
    void testGetPosts() {
        assertThat(this.getPostsService.getPosts()).hasSize(this.postRepository.findAll().size());
    }
}