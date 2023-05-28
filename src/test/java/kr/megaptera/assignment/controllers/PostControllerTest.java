package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CreatePostService;
import kr.megaptera.assignment.application.DeletePostService;
import kr.megaptera.assignment.application.GetPostService;
import kr.megaptera.assignment.application.UpdatePostService;
import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.PostUpdateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
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

@WebMvcTest(PostController.class)
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPostService getPostService;

    @MockBean
    private CreatePostService createPostService;

    @MockBean
    private UpdatePostService updatePostService;

    @MockBean
    private DeletePostService deletePostService;

    @Test
    void list() throws Exception {
        given(getPostService.getPostDtos()).willReturn(List.of(
            new PostDto("1", "작성자", "제목", "내용")
        ));

        mockMvc.perform(get("/posts"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("제목")
            ));
    }

    @Test
    void detail() throws Exception {
        given(getPostService.getPostDto(any())).willReturn(
            new PostDto("1", "작성자", "제목", "내용"));

        mockMvc.perform(get("/posts/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("작성자")
            ));
    }

    @Test
    void create() throws Exception {
        String json = """
            {
                "title" : "새 글",
                "author" : "작성자",
                "content" : "내용"
            }
            """;

        mockMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isCreated());

        verify(createPostService).create(any(PostCreateDto.class));
    }

    @Test
    void update() throws Exception {
        String postId = "1";

        String json = """
            {
                "title" : "수정 제목",
                "content" : "수정 내용"
            }
            """;
        mockMvc.perform(patch("/posts/" + postId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isNoContent());

        verify(updatePostService).update(eq(postId), any(PostUpdateDto.class));
    }

    @Test
    void deletePost() throws Exception {
        String postId = "1";

        mockMvc.perform(delete("/posts/" + postId))
            .andExpect(status().isNoContent());

        verify(deletePostService).delete(postId);
    }
}
