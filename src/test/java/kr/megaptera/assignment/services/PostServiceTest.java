package kr.megaptera.assignment.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import kr.megaptera.assignment.dtos.PostCreateDto;
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

    @Test
    @DisplayName("게시물 생성 테스트")
    void createPost(){
        // Given : CreateDto 를 입력받았다.
        PostCreateDto newPostCreateDto = new PostCreateDto("굉장한제목", "작성자", "엄청난내용");

        // When : Service 메서드 호출
        postService.savePost(newPostCreateDto);

        // Then : 레포지토리에서 메서드가 호출되었는지 확인
        verify(postRepository).saveAndFlush(any(Post.class));
    }

    @Test
    @DisplayName("게시물 상세 조회 테스트")
    void getDetailPost(){
        PostId postId = new PostId("POST0001");
        given(postRepository.findById(postId))
                .willReturn(Optional.of(new Post(
                        postId,
                        "제목",
                        "작성자",
                        "내용"
                )));

        PostDto postDto = postService.getPostDetail(postId.toString());

        assertThat(postDto.getId()).isEqualTo(postId.toString());
        assertThat(postDto.getTitle()).isEqualTo("제목");
        assertThat(postDto.getAuthor()).isEqualTo("작성자");
        assertThat(postDto.getContent()).isEqualTo("내용");
    }

}
