package kr.megaptera.assignment.application.posts;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.posts.UpdatePostDto;
import kr.megaptera.assignment.models.posts.Author;
import kr.megaptera.assignment.models.posts.Content;
import kr.megaptera.assignment.models.posts.Post;
import kr.megaptera.assignment.models.posts.Title;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Transactional
class UpdatePostServiceTest {
    UpdatePostService updatePostService;

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        updatePostService = new UpdatePostService(postRepository);
    }

    @Test
    @DisplayName("updatePost")
    void testUpdatePostIfExists() {
        UUID mockId = UUID.randomUUID();
        Title mockTitle = new Title("title");
        Author mockAuthor = new Author("author");
        Content mockContent = new Content("content");

        Title mockNewTitle = new Title("title2");
        Content mockNewContent = new Content("content2");

        Post mockPost = new Post(
                mockId,
                mockTitle,
                mockAuthor,
                mockContent
        );

        given(postRepository.findById(mockId)).willReturn(Optional.of(mockPost));

        UpdatePostDto mockUpdatePostDto = new UpdatePostDto(mockNewTitle.toString(), mockNewContent.toString());

        updatePostService.updatePost(mockId.toString(), mockUpdatePostDto);

        verify(postRepository).findById(mockId);

        assertThat(mockPost.getTitle()).isEqualTo(mockNewTitle);
        assertThat(mockPost.getContent()).isEqualTo(mockNewContent);
    }
}