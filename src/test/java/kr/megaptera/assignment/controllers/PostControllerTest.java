package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.entities.PostEntity;
import kr.megaptera.assignment.repositories.PostRepository;
import kr.megaptera.assignment.services.DetailPostService;
import kr.megaptera.assignment.services.ListPostService;
import kr.megaptera.assignment.vos.Author;
import kr.megaptera.assignment.vos.PostContent;
import kr.megaptera.assignment.vos.PostId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private ListPostService listPostService;

    @MockBean
    private PostRepository postRepository;

    @Test
    void list() throws Exception {
        List<PostEntity> postEntityList = IntStream.range(0, 10)
                .mapToObj(i -> PostEntity.of(
                        PostId.generate(),
                        "Title %d".formatted(i + 1),
                        Author.of("Author %d".formatted(i + 1)),
                        PostContent.of("PostContent %d".formatted(i + 1))
                ))
                .toList();
        given(postRepository.findAll()).willReturn(postEntityList);

        assertThat(listPostService.list()).hasSize(10);

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void detail() throws Exception {
        PostId id = PostId.generate();
        PostEntity entity = PostEntity.of(
                id, "Title", Author.of("Author"), PostContent.of("Plain Text")
        );
        given(postRepository.findById(id)).willReturn(Optional.of(entity));

        assertThat(postRepository.findById(id)).satisfies(e -> {
            assertThat(e).isNotNull();
        });

        mockMvc.perform(get("/posts/%s".formatted(id.toString())))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void create() throws Exception {
        String json = """
                {
                    "author": "the author",
                    "title": "the title",
                    "content": "the content"
                }
                """;
        mockMvc.perform(post("/posts")
                        .content(json)
                        .contentType("application/json")
                )
                .andExpect(status().isCreated());

        verify(postRepository).save(argThat(entity -> entity.getTitle().equals("the title")));
    }

    @Test
    void update() throws Exception {
        PostId postId = PostId.generate();
        given(postRepository.findById(postId)).willReturn(Optional.of(PostEntity.of(
                postId,
                "Title 1",
                Author.of("the author"),
                PostContent.of("the post content")
        )));
        String json = """
                {
                    "title": "the title",
                    "content": "the content"
                }
                """;
        mockMvc.perform(patch("/posts/%s".formatted(postId.toString()))
                        .content(json)
                        .contentType("application/json")
                )
                .andExpect(status().isNoContent());

        verify(postRepository).save(argThat(entity -> entity.getId().equals(postId)));
    }

    @Test
    void deletePost() throws Exception {
        PostId postId = PostId.generate();
        mockMvc.perform(delete("/posts/%s".formatted(postId.toString())))
                .andExpect(status().isNoContent());

        verify(postRepository).deleteById(argThat(id -> id.equals(postId)));
    }
}