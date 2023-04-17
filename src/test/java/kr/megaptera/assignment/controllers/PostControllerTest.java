package kr.megaptera.assignment.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import kr.megaptera.assignment.application.PostService;
import kr.megaptera.assignment.dtos.PostResponse;
import kr.megaptera.assignment.dtos.UpdatePostRequest;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @DisplayName("GET /posts")
    @Test
    void getPostList() throws Exception {
        // given
        given(postService.getPostList()).willReturn(
                List.of(new PostResponse("1", "제목1", "Harry", "내용1"),
                        new PostResponse("2", "제목2", "Potter", "내용2")));

        // when, then
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("제목1")));
    }

    @DisplayName("GET /posts/{id} - correctId")
    @Test
    void getPostSuccess() throws Exception {
        // given
        String id = "1";
        given(postService.getPost(id)).willReturn(
                new PostResponse(id, "제목", "Harry", "내용"));

        // when, then
        mockMvc.perform(get("/posts/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("제목")));
    }

    @DisplayName("GET /posts/{id} - inCorrectId")
    @Test
    void getPostFail() throws Exception {
        // given
        String id = "1";
        given(postService.getPost(id)).willThrow(PostNotFound.class);

        // when, then
        mockMvc.perform(get("/posts/" + id))
                .andExpect(status().isNotFound());
    }

    @DisplayName("POST /posts")
    @Test
    void createPostSuccess() throws Exception {
        // given
        String json = """
                {
                "title": "제목",
                "author": "Harry",
                "content": "내용"
                }
                """;

        // when, then
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(postService).createPost(any(Post.class));
    }

    @DisplayName("PATCH /posts/{id}")
    @Test
    void updatePost() throws Exception {
        // given
        String id = "1";
        String json = """
                {
                "title": "제목바뀜",
                "content": "내용바뀜"
                }
                """;

        // when, then
        mockMvc.perform(patch("/posts/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNoContent());

        verify(postService).updatePost(eq(id), any(UpdatePostRequest.class));

    }

    @DisplayName("DELETE /posts/{id}")
    @Test
    void deletePost() throws Exception {
        // given
        String id = "1";

        // when, then
        mockMvc.perform(delete("/posts/" + id))
                .andExpect(status().isNoContent());

        verify(postService).deletePost(any(String.class));
    }

}
