package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.comments.CreateCommentService;
import kr.megaptera.assignment.application.comments.DeleteCommentService;
import kr.megaptera.assignment.application.comments.GetCommentsService;
import kr.megaptera.assignment.application.comments.UpdateCommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateCommentService createCommentService;
    @MockBean
    private GetCommentsService getCommentsService;
    @MockBean
    private UpdateCommentService updateCommentService;
    @MockBean
    private DeleteCommentService deleteCommentService;

    @Test
    @DisplayName("댓글 작성 - POST /comments?postId={postId}")
    void testPostComment() throws Exception {
        String mockPostId = UUID.randomUUID().toString();
        String mockCommentAuthor = "author";
        String mockCommentContent = "content";

        String createCommentRequestBody = """
                {
                    "author": "%s",
                    "content": "%s"
                }
                """.formatted(mockCommentAuthor, mockCommentContent);

        RequestBuilder requestBuilder = post("/comments?postId=%s".formatted(mockPostId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(createCommentRequestBody);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());

        verify(this.createCommentService).createComment(any(), any());
    }

    @Test
    @DisplayName("댓글 조회 - GET /comments?postId={postId}")
    void testGetComment() throws Exception {
        String mockPostId = UUID.randomUUID().toString();

        RequestBuilder requestBuilder = get("/comments?postId=%s".formatted(mockPostId));

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

        verify(this.getCommentsService).getComments(mockPostId);
    }

    @Test
    @DisplayName("댓글 수정 - PATCH /comments/{id}?postId={postId}")
    void testPatchComment() throws Exception {
        String mockId = UUID.randomUUID().toString();
        String mockPostId = UUID.randomUUID().toString();

        String mockCommentContent = "content";

        String updateCommentRequestBody = """
                {
                    "content": "%s"
                }
                """.formatted(mockCommentContent);

        RequestBuilder requestBuilder = patch("/comments/%s?postId=%s".formatted(mockId, mockPostId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateCommentRequestBody);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent());

        verify(this.updateCommentService).updateComment(any(), any());
    }

    @Test
    @DisplayName("댓글 삭제 - DELETE /comments/{id}?postId={postId}")
    void testDeleteComment() throws Exception {
        String mockId = UUID.randomUUID().toString();
        String mockPostId = UUID.randomUUID().toString();

        RequestBuilder requestBuilder = delete("/comments/%s?postId=%s".formatted(mockId, mockPostId));

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent());

        verify(this.deleteCommentService).deleteComment(mockPostId, mockId);
    }
}