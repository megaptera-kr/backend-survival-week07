package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.request.RqUpdatePostDto;
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

class UpdatePostServiceTest {

    private PostRepository postRepository;

    private UpdatePostService updatePostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);

        updatePostService = new UpdatePostService(postRepository);
    }

     @Test
     void UpdatePost() {
         PostId postId = new PostId("test");
         Title title = new Title("title");
         Author author = new Author("author");
         Content content = new Content("content");

         Post post = new Post(postId, title, author, content);

         given(postRepository.findById(postId))
                 .willReturn(java.util.Optional.of(post));

         RqUpdatePostDto dto = new RqUpdatePostDto();
            dto.setTitle("title2");
            dto.setContent("content2");

         ResponseEntity<PostDto> updateDto = updatePostService.updatePost(dto, postId.toString());

            assertThat(updateDto.getBody().getTitle()).isEqualTo("title2");
            assertThat(updateDto.getBody().getContent()).isEqualTo("content2");


     }

}