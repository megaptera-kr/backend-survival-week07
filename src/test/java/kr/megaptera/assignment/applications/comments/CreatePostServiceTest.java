package kr.megaptera.assignment.applications.comments;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.applications.posts.CreatePostService;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@SpringBootTest
@Transactional
class CreatePostServiceTest {
    private PostRepository postRepository;
    private CreatePostService createPostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        createPostService = new CreatePostService(postRepository);
    }

    // TODO : (dh) 테스트를 어떻게 성공시킬것인가? 인메모리 디비가 꼭 필요하겠는디..?
//    @Test
//    void createPost(){
//        var createDto = new PostCreateDto("title", "author", "content");
//        given(postRepository.save()).willReturn(Optional.of(post));
//
//        var postReadDto = createPostService.execute(createDto);
//
//        assertThat(postReadDto.getTitle()).isEqualTo(createDto.getTitle());
//        assertThat(postReadDto.getAuthor()).isEqualTo(createDto.getAuthor());
//        assertThat(postReadDto.getContent()).isEqualTo(createDto.getContent());
//
//        var posts = postRepository.findAll();
//        assertThat(posts).hasSize(1);
//    }
}