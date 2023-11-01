package kr.megaptera.assignment.application.posts;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.posts.CreatePostDto;
import kr.megaptera.assignment.dtos.posts.PostDto;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CreatePostServiceTest {
    CreatePostService createPostService;

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void setUp() {
        createPostService = new CreatePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 생성")
    void testCreateService() {
        String mockTitle = "title";
        String mockAuthor = "author";
        String mockContent = "content";

        CreatePostDto mockCreatePostDto = new CreatePostDto(
                mockTitle,
                mockAuthor,
                mockContent
        );

        int initialPostSize = this.postRepository.findAll().size();

        PostDto postDto = createPostService.createPost(mockCreatePostDto);

        assertThat(postDto.getId()).isNotNull();

        assertThat(this.postRepository.findAll()).hasSize(initialPostSize + 1);
    }
}