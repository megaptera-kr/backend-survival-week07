package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CommentService;
import kr.megaptera.assignment.dtos.CommentDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    CommentService commentService;

    @Test
    @DisplayName("코멘트컨트롤러 comments")
    void comments() throws Exception {
        String postId = "001";
        given(commentService.getComments(postId)).willReturn(List.of(
                new CommentDto("1", "writer", "content"),
                new CommentDto("2", "test", "comment")
        ));

        mockMvc.perform(get("/comments").param("postId", postId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("코멘트컨트롤러 create")
    void create() throws Exception {
        String json = """
                {
                    "author" : "작성자",
                    "content" : "내용"
                }
                """;
        String postId = "1";

        mockMvc.perform(post("/comments").param("postId", postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("코멘트컨트롤러 update")
    void update() throws Exception {
        String json = """
                {
                    "author" : "작성자",
                    "content" : "내용"
                }
                """;
        String postId = "001";
        String id = "10";
        mockMvc.perform(patch("/comments/{id}", id)
                        .param("postId", postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("코멘트컨트롤러 delete")
    void deleteComment() throws Exception {
        String id = "100";
        String postId = "1";
        mockMvc.perform(delete("/comments/{id}", id)
                        .param("postId", postId))
                .andExpect(status().isNoContent());
    }
}