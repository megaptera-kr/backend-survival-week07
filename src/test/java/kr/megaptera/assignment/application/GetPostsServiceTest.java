package kr.megaptera.assignment.application;

import kr.megaptera.assignment.models.Author;
import kr.megaptera.assignment.models.Content;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.Title;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;

class GetPostsServiceTest {

    private PostRepository postRepository;

    private GetPostsService getPostsService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);

        getPostsService = new GetPostsService(postRepository);
    }

    @Test
    void GetPosts() {

        PostId postId = new PostId("test");

        Title title = new Title("title");
        Author author = new Author("author");
        Content content = new Content("content");

        Post post = new Post(postId, title, author, content);


        given(postRepository.findAll())
                .willReturn(java.util.List.of(post));

        assertThat(getPostsService.getPostList().getBody().size()).isEqualTo(1);
    }
}