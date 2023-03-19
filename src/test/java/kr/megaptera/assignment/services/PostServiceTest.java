package kr.megaptera.assignment.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.List;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PostServiceTest {

    private PostRepository postRepository;

    private PostService postService;

    @BeforeEach
    void setUp() {
        // postRepository 를 mocking 하여 할당
        postRepository = mock(PostRepository.class);

        // PostService 생성
        postService = new PostService(postRepository);
    }

    @Test
    @DisplayName("게시물 목록 조회")
    void getPostList() {
        // Given : 레포 반환값 설정?
        given(postRepository.findAll()).willReturn(
            List.of(new Post(
                new PostId("ID0701"),
                "제목",
                "작성한사람",
                "그냥내용")));

        // When : 서비스에서 메서드 호출
        List<PostDto> postList = postService.getPostList();

        // Then : 결과값 확인
        assertThat(postList).hasSize(1);
    }

}
