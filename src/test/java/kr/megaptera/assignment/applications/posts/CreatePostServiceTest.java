package kr.megaptera.assignment.applications.posts;

import kr.megaptera.assignment.dtos.posts.PostCreateDto;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class CreatePostServiceTest {
    private PostRepository postRepository;
    private CreatePostService createPostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        createPostService = new CreatePostService(postRepository);
    }

    @Test
    void createPost(){
        var postCreateDto = new PostCreateDto("title", "author", "content");
        var postReadDto = createPostService.execute(postCreateDto);

        assertThat(postReadDto.getTitle()).isEqualTo(postCreateDto.getTitle());
        assertThat(postReadDto.getAuthor()).isEqualTo(postCreateDto.getAuthor());
        assertThat(postReadDto.getContent()).isEqualTo(postCreateDto.getContent());
    }
}