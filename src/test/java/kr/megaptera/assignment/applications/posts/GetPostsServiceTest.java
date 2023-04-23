package kr.megaptera.assignment.applications.posts;

import kr.megaptera.assignment.applications.posts.GetPostsService;
import kr.megaptera.assignment.dtos.posts.PostReadDto;
import kr.megaptera.assignment.models.posts.MultilineText;
import kr.megaptera.assignment.models.posts.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("get post list")
    void list() {
        var posts = List.of(
                new Post(
                        "post_id_01",
                        "title",
                        "author",
                        new MultilineText("content")));

        given(postRepository.findAll()).willReturn(posts);
        List<PostReadDto> postDtos = getPostsService.execute().stream().toList();
        assertThat(postDtos).hasSize(1);
    }
}
