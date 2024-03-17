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
        PostId postId = new PostId("001POST");
        Post post = new Post(postId, "TITLE", "AUTHOR", new MultilineText("TITLE"));

        given(postRepository.findById(postId)).willReturn(Optional.of(post));

        PostUpdateDto postUpdateDto = new PostUpdateDto("UPDATED_TITLE", "UPDATED_CONTENT");

        updatePostService.updatePost(postId.toString(), postUpdateDto);

        assertThat(post.title()).isEqualTo("UPDATED_TITLE");
        assertThat(post.content()).isEqualTo(new MultilineText("UPDATED_CONTENT"));
    }
}