package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.applications.post.*;
import kr.megaptera.assignment.dtos.post.*;
import kr.megaptera.assignment.exceptions.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
public class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPostDtosService getPostDtosService;

    @MockBean
    private GetPostDtoService getPostDtoService;

    @MockBean
    private CreatePostDtoService createPostDtoService;

    @MockBean
    private UpdatePostDtoService updatePostDtoService;

    @MockBean
    private DeletePostDtoService deletePostDtoService;


    @Test
    @DisplayName("GET /posts")
    void getPostDtos() throws Exception {
        given(getPostDtosService.getPostDtos()).willReturn(
                List.of(
                        new PostDTO("id_1", "title_1", "jyh", "content_1"),
                        new PostDTO("id_2", "title_2", "bjs", "content_2")
                ));

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("jyh")));
    }

    @Test
    @DisplayName("Get /posts/{id}")
    void getPostDto() throws Exception {
        String postId = "ID_001";

        given(getPostDtoService.getPostDto(postId)).willReturn(
                new PostDTO(postId, "title", "jyh", "content")
        );

        mockMvc.perform(get("/posts/" + postId))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("jyh")));


    }

    @Test
    @DisplayName("GET /posts/{id} with Wrong ID")
    void getPostDtoWithWrongId() throws Exception {
        String postId = "WrongId";

        given(getPostDtoService.getPostDto(postId)).willThrow(PostNotFound.class);

        mockMvc.perform(get("/posts/" + postId))
                .andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("POST /posts")
    void createPost() throws Exception {
        String body = """
                {
                    "title": "test_1",
                    "author": "jyh",
                    "content": "hahahaha"
                }
                """;

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("PATCH /posts/{id}")
    void updatePost() throws Exception {
        String postId = "ID_001";
        String body = """
                {
                    "title": "test",
                    "content": "check!"
                }
                """;

        mockMvc.perform(patch("/posts/" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        String postId = "ID_001";

        mockMvc.perform(delete("/posts/" + postId))
                .andExpect(status().isNoContent());
    }
}
