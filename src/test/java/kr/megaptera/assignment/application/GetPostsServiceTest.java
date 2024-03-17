package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPostsServiceTest {

    private PostRepository postRepository;

    private GetPostsService getPostsService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);

        getPostsService = new GetPostsService(postRepository);
    }

    @Test
    void list() {
        given(postRepository.findAll())
                .willReturn(List.of(new Post(
                        new PostId("001POST"),
                        "TITLE",
                        "AUTHOR",
                        new MultilineText("CONTENT")
                )));

        List<PostDto> postDtos = getPostsService.getPostDtos();

        assertThat(postDtos).hasSize(1);
    }
}