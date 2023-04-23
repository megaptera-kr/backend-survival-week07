package kr.megaptera.assignment.applications.posts;

import kr.megaptera.assignment.applications.posts.UpdatePostService;
import kr.megaptera.assignment.dtos.posts.PostUpdateDto;
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

class UpdatePostServiceTest {

    private PostRepository postRepository;
    private UpdatePostService updatePostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        updatePostService = new UpdatePostService(postRepository);
    }

    @Test
    @DisplayName("update post")
    void update() throws PostNotFoundException {
        var post = new Post(
                "update_post_id",
                "title",
                "content",
                new MultilineText("content"));

        given(postRepository.findById(post.getId())).willReturn(Optional.of(post));

        var id = post.getId().toString();
        var postUpdateDto = new PostUpdateDto("업데이트 된 제목", "업데이트 된 내용");

        var postReadDto = updatePostService.execute(id, postUpdateDto);

        assertThat(postReadDto.getId()).isEqualTo(id);
        assertThat(postReadDto.getTitle()).isEqualTo(postUpdateDto.getTitle());
        assertThat(postReadDto.getAuthor()).isEqualTo(post.getAuthor());
        assertThat(postReadDto.getContent()).isEqualTo(postUpdateDto.getContent());
    }
}
