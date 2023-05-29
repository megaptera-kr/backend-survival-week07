package kr.megaptera.assignment.application;

import kr.megaptera.assignment.models.Author;
import kr.megaptera.assignment.models.Content;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.Title;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeletePostServiceTest {

    private PostRepository postRepository;

    private DeletePostService deletePostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);

        deletePostService = new DeletePostService(postRepository);
    }

    @Test
    void DeletePost() {
        PostId postId = new PostId("test");

        Title title = new Title("title");
        Author author = new Author("author");
        Content content = new Content("content");

        Post post = new Post(postId, title, author, content);

        given(postRepository.findById(postId))
                .willReturn(java.util.Optional.of(post));

        deletePostService.deletePost(postId.toString());

        verify(postRepository).delete(any(Post.class));
    }

}