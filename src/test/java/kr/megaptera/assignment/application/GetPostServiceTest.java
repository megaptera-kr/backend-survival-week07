package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
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

class GetPostServiceTest {

    private PostRepository postRepository;

    private GetPostService getPostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);

        getPostService = new GetPostService(postRepository);
    }

    @Test
    void detail() {
        PostId postId = new PostId("001POST");

        given(postRepository.findById(postId))
            .willReturn(Optional.of(new Post(
                    postId,
                    "TITLE",
                    "AUTHOR",
                    new MultilineText("CONTENT")
            )));

        PostDto postDto = getPostService.getPostDto(postId.toString());

        assertThat(postDto.getId()).isEqualTo(postId.toString());
        assertThat(postDto.getTitle()).isEqualTo("TITLE");
        assertThat(postDto.getAuthor()).isEqualTo("AUTHOR");
        assertThat(postDto.getContent()).isEqualTo("CONTENT");
    }
}