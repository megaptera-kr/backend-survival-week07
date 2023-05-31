package kr.megaptera.assignment.applications.posts;

import kr.megaptera.assignment.applications.posts.DeletePostService;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.models.posts.MultilineText;
import kr.megaptera.assignment.models.posts.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeletePostServiceTest {
    private PostRepository postRepository;
    private DeletePostService deletePostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        deletePostService = new DeletePostService(postRepository);
    }

    @Test
    @DisplayName("remove post")
    void delete() throws PostNotFoundException {
        var post = new Post("remove_post_id", "title", "author", new MultilineText("content"));
        given(postRepository.findById(post.getId())).willReturn(Optional.of(post));

        var postReadDto = deletePostService.execute(post.getId().toString());
        assertThat(postReadDto.getId()).isEqualTo(post.getId().toString());
        assertThat(postReadDto.getTitle()).isEqualTo(post.getTitle());
        assertThat(postReadDto.getAuthor()).isEqualTo(post.getAuthor());
        assertThat(postReadDto.getContent()).isEqualTo(post.getContent().toString());
    }
}
