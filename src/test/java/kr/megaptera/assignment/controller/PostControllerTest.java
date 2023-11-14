package kr.megaptera.assignment.controller;

import kr.megaptera.assignment.dto.PostCreateRequest;
import kr.megaptera.assignment.dto.PostResponse;
import kr.megaptera.assignment.dto.PostUpdateRequest;
import kr.megaptera.assignment.model.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import kr.megaptera.assignment.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    private final String postId = "testPostId";

    @Test
    void shouldReturnPostsList() throws Exception {

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnPostDetail() throws Exception {

        mockMvc.perform(get("/posts/" + postId))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreatePost() throws Exception {
        String jsonRequest = "{\"title\":\"Test Title\", \"content\":\"Test Content\"}";

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdatePost() throws Exception {
        String jsonRequest = "{\"title\":\"Updated Title\", \"content\":\"Updated Content\"}";

        mockMvc.perform(patch("/posts/" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldDeletePost() throws Exception {
        mockMvc.perform(delete("/posts/" + postId))
                .andExpect(status().isNoContent());
    }
}