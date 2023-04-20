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

import static org.hamcrest.core.StringContains.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private GetCommentsService getCommentsService;

    @MockBean
    private CreateCommentService createCommentService;

    @MockBean
    private UpdateCommentService updateCommentService;

    @MockBean
    private DeleteCommentService deleteCommentService;

    @Test
    @DisplayName("GET /comments")
    void list() throws Exception {
        String commentId = "0001";
        String id = "001";

        given(getCommentsService.getCommentDtos(id))
                .willReturn(List.of(new CommentDto(commentId, "author", "content")
                ));

        mockMvc.perform(get("/comments?postId=" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("content")));

    }

    @Test
    @DisplayName("POST /comments?postId={postId}")
    void createComment() throws Exception {
        String postId = "001";

        String json = """
                {
                    "author" : "me",
                    "content" : "no"
                }
                """;

        mockMvc.perform(post("/comments?postId=" + "postId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(createCommentService).createCommentDto(any(), any(CommentCreateDto.class));
    }

    @Test
    void updateComment() throws Exception {
        String commentId = "0001";
        String id = "001";

        String json = """
                {
                    "content" : "new_content"
                }
                """;

        mockMvc.perform(patch("/comments/" + commentId + "?postId=" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        verify(updateCommentService).updateCommentDto(eq(commentId), eq(id), any(CommentUpdateDto.class));
    }

    @Test
    void deleteComment() throws Exception {
        String id = "001";
        String commentId = "0001";

        mockMvc.perform(delete("/comments/" + commentId + "?postId=" + id))
                .andExpect(status().isNoContent());

        verify(deleteCommentService).delete(eq(commentId), eq(id));
    }
}