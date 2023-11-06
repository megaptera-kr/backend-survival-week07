package kr.megaptera.assignment.application.posts;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.models.columns.Author;
import kr.megaptera.assignment.models.columns.Content;
import kr.megaptera.assignment.models.columns.Title;
import kr.megaptera.assignment.models.posts.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Transactional
class DeletePostServiceTest {
    DeletePostService deletePostService;

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        deletePostService = new DeletePostService(postRepository);
    }

    @Test
    @DisplayName("deletePost")
    void testDeletePost() {
        UUID mockId = UUID.randomUUID();
        Title mockTitle = new Title("title");
        Author mockAuthor = new Author("author");
        Content mockContent = new Content("content");

        Post mockPost = new Post(
                mockId,
                mockTitle,
                mockAuthor,
                mockContent
        );

        given(postRepository.findById(mockId)).willReturn(Optional.of(mockPost));

        deletePostService.deletePost(mockId.toString());

        verify(postRepository).findById(mockId);
        verify(postRepository).delete(mockPost);
    }
}