package kr.megaptera.assignment.application.posts;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.posts.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.posts.Author;
import kr.megaptera.assignment.models.posts.Content;
import kr.megaptera.assignment.models.posts.Post;
import kr.megaptera.assignment.models.posts.Title;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@SpringBootTest
@Transactional
class GetPostServiceTest {
    GetPostService getPostService;

    PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        getPostService = new GetPostService(postRepository);
    }

    @Test
    @DisplayName("id로 post를 찾은 경우 PostDto 반환")
    void testGetPostWhenPostExists() {
        UUID mockId = UUID.randomUUID();
        Title mockTitle = new Title("title");
        Author mockAuthor = new Author("author");
        Content mockContent = new Content("content");

        given(postRepository.findById(mockId)).willReturn(Optional.of(new Post(
                mockId,
                mockTitle,
                mockAuthor,
                mockContent
        )));

        PostDto postDto = getPostService.getPost(mockId.toString());

        assertThat(postDto.getId()).isEqualTo(mockId.toString());
        assertThat(postDto.getTitle()).isEqualTo(mockTitle.toString());
        assertThat(postDto.getAuthor()).isEqualTo(mockAuthor.toString());
        assertThat(postDto.getContent()).isEqualTo(mockContent.toString());
    }

    @Test
    @DisplayName("id로 post를 찾지 못한 경우 PostNotFound 에러 발생")
    void testGetPostWhenPostNotExists() {
        UUID mockId = UUID.randomUUID();

        given(postRepository.findById(mockId)).willReturn(Optional.empty());

        assertThatThrownBy(() -> getPostService.getPost(mockId.toString())).isInstanceOf(PostNotFound.class);
    }

}