package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.applications.*;
import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.PostId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private GetPostsService getPostsService;

    @MockBean
    private GetPostService getPostService;

    @MockBean
    private CreatePostService createPostService;

    @MockBean
    private UpdatePostService updatePostService;

    @MockBean
    private DeletePostService deletePostService;

    @Test
    @DisplayName("GET /posts")
    void findAll_test() throws Exception {
        // given
        given(getPostsService.getPosts()).willReturn(List.of(
                new PostDto("001","me","hi", "haha"),
                new PostDto("002","me2","hi2", "haha2"),
                new PostDto("003","me3","hi3", "haha3")
        ));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/posts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        containsString("me")
                ));
    }

    @Test
    @DisplayName("GET /posts/{id} - with incorrect ID")
    void detail() throws Exception {
        String id = "23";

        given(getPostService.findById(id))
                .willThrow(new PostNotFound());

        mockMvc.perform(get("/posts/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /posts")
    void create() throws Exception {
        String json = """
                {
                    "title" : "hi",
                    "author" : "isaac",
                    "content": "cheer up!"
                }
                """;

        mockMvc.perform(post("/posts")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated());

        verify(createPostService).createPost(any(PostCreateDto.class));
    }

    @Test
    @DisplayName("PATCH /posts/{id}")
    void update_test() throws Exception {
        String id = "0BSE8TXD2X8EN";

        String json = """
                {
                    "title" : "hello",
                    "content":"안뇽하세요"
                }
                """;

        mockMvc.perform(patch("/posts/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isOk());

        verify(updatePostService).updatePost(any(String.class), any(PostUpdateDto.class));
    }

    @Test
    @DisplayName("DELTE /posts/{id}")
    void delete_post_test() throws Exception {
        String id = "0001";

        mockMvc.perform(delete("/posts/"+id))
                .andExpect(status().isNoContent());

        verify(deletePostService).deletePost(any(String.class));
    }
}