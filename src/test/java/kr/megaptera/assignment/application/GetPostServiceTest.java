package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Author;
import kr.megaptera.assignment.models.Content;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.Title;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;

class GetPostServiceTest {

    private PostRepository postRepository;

    private GetPostService getPostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);

        getPostService = new GetPostService(postRepository);
    }

    @Test
    void GetPost() {

        PostId postId = new PostId("test");
        Title title = new Title("title");
        Author author = new Author("author");
        Content content = new Content("content");

        Post post = new Post(postId, title, author, content);

        given(postRepository.findById(postId))
                .willReturn(java.util.Optional.of(post));

        ResponseEntity<PostDto> postById = getPostService.getPostById(postId.toString());

        assertThat(postById.getBody().getId()).isEqualTo("test");
        assertThat(postById.getBody().getTitle()).isEqualTo("title");
        assertThat(postById.getBody().getAuthor()).isEqualTo("author");
        assertThat(postById.getBody().getContent()).isEqualTo("content");
    }
}