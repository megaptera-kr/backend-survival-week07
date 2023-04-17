package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.applications.comment.*;
import kr.megaptera.assignment.dtos.comment.*;
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

@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private GetCommentDtosService getCommentDtosService;

    @MockBean
    private CreateCommentService createCommentService;

    @MockBean
    private UpdateCommentService updateCommentService;

    @MockBean
    private DeleteCommentService deleteCommentService;

    @Test
    @DisplayName("GET /comments?postId={postId}")
    void getComments() throws Exception {
        String postId = "post_001";
        given(getCommentDtosService.getCommentDtos(postId)).willReturn(
                List.of(
                        new CommentDTO(postId, "jyh", "I can do it"),
                        new CommentDTO(postId, "bjs", "I can do it")
                ));

        mockMvc.perform(get("/comments?postId=" + postId))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("bjs")));
    }

    @Test
    @DisplayName("POST /comments?postId={postId}")
    void createComment() throws Exception {
        String postId = "post_001";
        String body = """
                {
                    "author": "pikachu",
                    "content": "pi ka pi ka!!"
                }
                """;

        mockMvc.perform(post("/comments?postId=" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("PATCH /comments/{id}?postId={postId}")
    void updateComment() throws Exception {
        String postId = "post_001";
        String commentId = "comment_001";
        String body = """
                {
                    "content": "GGO buk!"
                }
                """;
        mockMvc.perform(patch("/comments/" + commentId + "?postId=" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /comments/{id}?postId={postId}")
    void deleteComment() throws Exception {
        String postId = "post_001";
        String commentId = "comment_001";
        mockMvc.perform(delete("/comments/" + commentId + "?postId=" + postId))
                .andExpect(status().isNoContent());
    }
}
