package kr.megaptera.assignment.applications.post;

import jakarta.transaction.*;
import kr.megaptera.assignment.dtos.post.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@SpringBootTest
@Transactional
class GetPostDtoServiceTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private GetPostDtoService getPostDtoService;

//    @BeforeEach
//    void setup() {
//        PostRepository postRepository = mock(PostRepository.class);
//        getPostDtoService = new GetPostDtoService(postRepository);
//    }

    @Test
    @DisplayName("상세보기 GetPostDTO")
    void getPostDto() {
        String postId = "post_001";

        given(getPostDtoService.getPostDto(postId))
                .willReturn(
                        new PostDTO(
                                postId,
                                "title",
                                "jyh",
                                "hahaha")
                );
        PostDTO postDto = getPostDtoService.getPostDto(postId);

        assertThat(postDto.getId().equals(postId));
        assertThat(postDto.getTitle().equals("title"));
        assertThat(postDto.getAuthor().equals("jyh"));
        assertThat(postDto.getContent().equals("hahaha"));
    }

}