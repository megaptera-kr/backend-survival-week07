package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPostServiceTest {
    private PostRepository postRepository;

    private GetPostService getPostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);

        getPostService = new GetPostService(postRepository);
    }

    private static class TEST_POST {
        private static final Post ONE = new Post(PostId.of("1"), "작성자1", "제목1", MultilineText.of("내용"));
    }

    @Test
    void list() {
        given(postRepository.findAll()).willReturn(List.of(TEST_POST.ONE));

        List<PostDto> postDtos = getPostService.getPostDtos();

        assertThat(postDtos).hasSize(1);
        assertThat(postDtos.get(0).getTitle()).isEqualTo("제목1");
    }

    @Test
    void detail() {
        PostId postId = new PostId("1");
        given(postRepository.findById(any())).willReturn(
            Optional.of(TEST_POST.ONE)
        );

        PostDto postDto = getPostService.getPostDto(postId.toString());

        assertThat(postDto).isNotNull();
        assertThat(postDto.getTitle()).isEqualTo("제목1");
        assertThat(postDto.getAuthor()).isEqualTo("작성자1");
        assertThat(postDto.getContent()).isEqualTo("내용");
    }
}
