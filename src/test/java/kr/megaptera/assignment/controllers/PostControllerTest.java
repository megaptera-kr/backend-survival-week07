package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.PostService;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    @DisplayName("포스트컨트롤러 getPosts")
    void getPosts() throws Exception {
        given(postService.getList()).willReturn(List.of(
                new PostDto("1", "제목", "으히히", "내용"),
                new PostDto("2", "제목2", "으히히2", "내용2")
        ));

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("으히히")));
    }

    @Test
    @DisplayName("포스트컨트롤러 getPost")
    void getPost() throws Exception {
        String id = "122";
        given(postService.getPost(id)).willReturn(new PostDto(id, "111", "211", "123"));

        mockMvc.perform(get("/posts/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("123")))
                .andExpect(content().string(containsString("211")))
                .andExpect(content().string(containsString("111")));
    }

    @Test
    @DisplayName("포스트 컨트롤러 NORFOUND")
    void notFound() throws Exception {
        String id = "00100";
        given(postService.getPost(id)).willThrow(new PostNotFound());

        mockMvc.perform(get("/posts/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("포스트컨트롤러 Create")
    void create() throws Exception {
        String json = """
                {
                    "title" : "새 글",
                    "author" : "작성자",
                    "content" : "내용"
                }
                """;
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("포스트컨트롤러 update")
    void update() throws Exception {
        String json = """
                {
                    "title" : "새 글2",
                    "author" : "작성자2",
                    "content" : "내용2"
                }
                """;
        String id = "100";

        mockMvc.perform(MockMvcRequestBuilders.patch("/posts/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNoContent());

    }

    @Test
    @DisplayName("포스트컨트롤러 delete")
    void delete() throws Exception {
        String id = "100";

        mockMvc.perform(MockMvcRequestBuilders.delete("/posts/{id}", id))
                .andExpect(status().isNoContent());
    }
}