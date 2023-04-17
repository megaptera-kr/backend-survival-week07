package kr.megaptera.assignment.applications.post;

import jakarta.transaction.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class GetPostDtosServiceTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private GetPostDtosService getPostDtosService;

//    @BeforeEach
//    void setup() {
//        PostRepository mock = mock(PostRepository.class);
//        this.getPostDtosService = new GetPostDtosService(mock);
//    }

    @Test
    @DisplayName("게시글 목록 조회")
    void getPostDTOs() {
        postRepository.saveAll(
                List.of(
                        new Post(
                                "ID_001",
                                Title.of("title_1"),
                                Author.of("author_1"),
                                Content.of("content_1")),
                        new Post(
                                "ID_002",
                                Title.of("title_2"),
                                Author.of("author_2"),
                                Content.of("content_2"))
                )
        );

        assertThat(getPostDtosService.getPostDtos()).hasSize(2);
    }
}