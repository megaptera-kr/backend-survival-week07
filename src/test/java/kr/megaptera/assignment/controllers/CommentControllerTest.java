package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CreateCommentService;
import kr.megaptera.assignment.application.DeleteCommentService;
import kr.megaptera.assignment.application.GetCommentService;
import kr.megaptera.assignment.application.UpdateCommentService;
import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.CommentUpdateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
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

@WebMvcTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetCommentService getCommentService;

    @MockBean
    private CreateCommentService createCommentService;

    @MockBean
    private UpdateCommentService updateCommentService;

    @MockBean
    private DeleteCommentService deleteCommentService;

    @Test
    void list() throws Exception {
        given(getCommentService.getCommentDtos(any())).willReturn(List.of(
            new CommentDto("1", "작성자", "댓글 내용")
        ));

        mockMvc.perform(get("/comments")
                .param("postId", "1"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("댓글 내용")
            ));
    }

    @Test
    void create() throws Exception {
        String postId = "1";
        String json = """
            {
                "author" : "작성자",
                "content" : "댓글 내용"
            }
            """;

        mockMvc.perform(post("/comments")
                .param("postId", postId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isCreated());

        verify(createCommentService).create(eq(postId), any(CommentCreateDto.class));
    }

    @Test
    void update() throws Exception {
        String commentId = "1";
        String postId = "1";

        String json = """
            {
                "content" : "댓글 내용"
            }
            """;

        mockMvc.perform(patch("/comments/" + commentId)
            .param("postId", postId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isNoContent());

        verify(updateCommentService).update(eq(postId), eq(commentId), any(CommentUpdateDto.class));
    }

    @Test
    void deleteComment() throws Exception {
        String commentId = "1";
        String postId = "1";

        mockMvc.perform(delete("/comments/" + commentId)
                .param("postId", postId)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        verify(deleteCommentService).delete(eq(commentId), eq(postId));
    }
}
