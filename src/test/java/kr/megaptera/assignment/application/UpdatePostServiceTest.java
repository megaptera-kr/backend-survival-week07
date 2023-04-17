package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostUpdateRequestDto;
import kr.megaptera.assignment.entities.PostEntity;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
  @DisplayName("게시물 수정")
  void update() {
    String postId = "1";

    PostEntity post =
        new PostEntity(postId, "제목", "작성자", "내용");

    given(postRepository.findById(postId)).willReturn(Optional.ofNullable(post));

    PostUpdateRequestDto postUpdateDto =
        new PostUpdateRequestDto("변경된 제목", "변경된 내용");

    updatePostService.updatePost(postId.toString(), postUpdateDto);

    assertThat(post.getTitle()).isEqualTo("변경된 제목");
    assertThat(post.getContent()).isEqualTo("변경된 내용");
  }
}
