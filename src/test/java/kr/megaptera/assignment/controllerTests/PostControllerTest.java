package kr.megaptera.assignment.controllerTests;

import kr.megaptera.assignment.applications.*;
import kr.megaptera.assignment.controllers.*;
import kr.megaptera.assignment.dtos.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private GetPostsService getPostsService;

    @MockBean
    private GetPostService getPostService;

    @MockBean
    private CreatePostService createPostService;

    @MockBean
    private UpdatePostService updatePostService;

    @MockBean
    private DeletePostService deletePostService;


    @Test
    @DisplayName("GET /posts")
    void list() throws Exception {
        given(getPostsService.getList()).
                willReturn(List.of(new PostDto("001", "author", "title", "content")));

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("title")
                ));
    }

    @Test
    @DisplayName("/Get/{id}")
    void detail() throws Exception {
        String id = "001";
        given(getPostService.getPostDto(id))
                .willReturn(new PostDto(id, "author", "title", "content"));

        mockMvc.perform(get("/posts/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("content")));
    }

    @Test
    void create() throws Exception {
        String json = """
                    {
                    "title" : "wow",
                    "author" : "jack",
                    "content" : "english?"
                    }
                """;

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isCreated());

        verify(createPostService).createPost(any(PostCreateDto.class));
    }

    @Test
    @DisplayName("updateTest")
    void update() throws Exception {
        String id = "001";

        String json = """
                {
                    "title" : "new title",
                    "content" : "new content"
                }
                """;

        mockMvc.perform(patch("/posts/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNoContent());

        verify(updatePostService).updatePost(eq(id), any(PostUpdateDto.class));
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        String id = "001";

        mockMvc.perform(delete("/posts/" + id)).andExpect(status().isNoContent());
        verify(deletePostService).deletePost(id);
    }

}