package kr.megaptera.assignment.applications.comments;

import kr.megaptera.assignment.applications.posts.GetPostService;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.models.posts.MultilineText;
import kr.megaptera.assignment.models.posts.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @DisplayName("get post")
    void detail() throws PostNotFoundException {

        var post = new Post("get_post_id", "title", "author", new MultilineText("content"));

        given(postRepository.findById(post.getId())).willReturn(Optional.of(post));

        var postReadDto = getPostService.execute(post.getId().toString());

        assertThat(postReadDto.getId()).isEqualTo(post.getId().toString());
        assertThat(postReadDto.getTitle()).isEqualTo(post.getTitle());
        assertThat(postReadDto.getAuthor()).isEqualTo(post.getAuthor());
        assertThat(postReadDto.getContent()).isEqualTo(post.getContent().toString());
    }

    @Test
    @DisplayName("fail get post")
    void getFail() throws PostNotFoundException {

        var post = new Post("fail_get_post_id", "title", "author", new MultilineText("content"));

        given(postRepository.findById(post.getId())).willReturn(Optional.empty());

        assertThrows(PostNotFoundException.class, () -> {
            getPostService.execute(post.getId().toString());
        });
    }
}
