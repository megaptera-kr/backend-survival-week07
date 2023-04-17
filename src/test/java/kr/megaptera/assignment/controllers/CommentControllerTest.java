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
import kr.megaptera.assignment.application.CommentService;
import kr.megaptera.assignment.dtos.CommentResponse;
import kr.megaptera.assignment.dtos.CreateCommentRequest;
import kr.megaptera.assignment.dtos.UpdateCommentRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @DisplayName("GET /comments?postId={postId}")
    @Test
    void getComments() throws Exception {
        // given
        String postId = "1";
        given(commentService.getComments(postId)).willReturn(
                List.of(new CommentResponse("1", postId, "Harry", "댓글1"),
                        new CommentResponse("2", postId, "Harry", "댓글2"))
        );

        // when, then
        mockMvc.perform(get("/comments?postId=" + postId))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("댓글1")));

    }

    @DisplayName("POST /comments?postId={postId}")
    @Test
    void createComment() throws Exception {
        // given
        String postId = "1";
        String json = """
                {
                "author": "Harry",
                "content": "댓글"
                }
                """;

        // when, then
        mockMvc.perform(post("/comments?postId=" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(commentService).createComment(eq(postId), any(CreateCommentRequest.class));
    }

    @DisplayName("PATCH /comments/{id}?postId={postId}")
    @Test
    void updateComment() throws Exception {
        // given
        String id = "1";
        String postId = "1";
        String json = """
                {
                "content": "댓글바뀜"
                }
                """;

        // when, then
        mockMvc.perform(patch("/comments/" + id + "?postId=" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNoContent());

        verify(commentService).updateComment(eq(id), eq(postId), any(UpdateCommentRequest.class));
    }

    @DisplayName("DELETE /comments/{id}?postId={postId}")
    @Test
    void deleteComment() throws Exception {
        // given
        String id = "1";
        String postId = "1";

        // when, then
        mockMvc.perform(delete("/comments/" + id + "?postId=" + postId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(commentService).deleteComment(eq(id), eq(postId));
    }
}
