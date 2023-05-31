package kr.megaptera.assignment.application;

import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

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
    void delete() {
        PostId postId = new PostId("1");
        Post post = new Post(postId, "작성자", "제목", MultilineText.of("내용"));

        given(postRepository.findById(any())).willReturn(Optional.of(post));

        deletePostService.delete(postId.toString());

        verify(postRepository).delete(any(Post.class));
    }
}