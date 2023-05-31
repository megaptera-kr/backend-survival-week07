package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UpdatePostServiceTest {
    private PostRepository postRepository;
    private UpdatePostService updatePostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);

        updatePostService = new UpdatePostService(postRepository);
    }

    @Test
    void update() {
        PostId postId = new PostId("1");
        PostUpdateDto postUpdateDto = new PostUpdateDto("수정 제목", "수정 내용");
        Post post = new Post(postId, "작성자", "제목", MultilineText.of("내용"));

        given(postRepository.findById(any())).willReturn(Optional.of(post));

        updatePostService.update(postId.toString(), postUpdateDto);

        assertThat(post.title()).isEqualTo("수정 제목");
        assertThat(post.content().toString()).isEqualTo("수정 내용");
    }
}
