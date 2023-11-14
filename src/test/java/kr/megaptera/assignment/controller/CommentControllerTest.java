package kr.megaptera.assignment.controller;


import kr.megaptera.assignment.dto.PostCreateRequest;
import kr.megaptera.assignment.dto.PostResponse;
import kr.megaptera.assignment.dto.PostUpdateRequest;
import kr.megaptera.assignment.model.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import kr.megaptera.assignment.service.CommentService;
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

@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    // 예시를 위한 테스트 데이터
    private final String postId = "testPostId";
    private final String commentId = "testCommentId";

    @BeforeEach
    void setUp() {
        // 필요한 경우 여기에 초기화 코드를 작성합니다.
    }

    @Test
    void shouldReturnCommentsList() throws Exception {
        mockMvc.perform(get("/comments")
                        .param("postId", postId))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateComment() throws Exception {
        String jsonRequest = "{\"content\":\"Test Comment\"}";

        mockMvc.perform(post("/comments")
                        .param("postId", postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateComment() throws Exception {
        String jsonRequest = "{\"content\":\"Updated Comment\"}";

        mockMvc.perform(patch("/comments/" + commentId)
                        .param("postId", postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldDeleteComment() throws Exception {
        mockMvc.perform(delete("/comments/" + commentId)
                        .param("postId", postId))
                .andExpect(status().isNoContent());
    }
}

