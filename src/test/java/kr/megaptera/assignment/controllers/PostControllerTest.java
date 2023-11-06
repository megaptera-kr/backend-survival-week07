package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.posts.*;
import kr.megaptera.assignment.dtos.posts.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreatePostService createPostService;

    @MockBean
    private GetPostsService getPostsService;

    @MockBean
    private GetPostService getPostService;

    @MockBean
    private UpdatePostService updatePostService;

    @MockBean
    private DeletePostService deletePostService;

    @Test
    @DisplayName("게시글 작성 - POST /posts")
    void testCreatePost() throws Exception {
        String mockTitle = "title";
        String mockAuthor = "author";
        String mockContent = "content";

        String createPostRequestData = """
                {
                    "title": "%s",
                    "author": "%s",
                    "content": "%s"
                }
                """.formatted(mockTitle, mockAuthor, mockContent);

        RequestBuilder requestBuilder = post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createPostRequestData);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("게시글 조회 - GET /posts")
    void testGetPosts() throws Exception {
        RequestBuilder requestBuilder = get("/posts");

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("게시글 상세 조회(id로 post를 찾은 경우 PostDto 반환) - GET /posts/{id}")
    void testGetPostIfExists() throws Exception {
        String mockId = UUID.randomUUID().toString();
        String mockTitle = "title";
        String mockAuthor = "author";
        String mockContent = "content";

        given(this.getPostService.getPost(any())).willReturn(new PostDto(
                mockId,
                mockTitle,
                mockAuthor,
                mockContent
        ));

        RequestBuilder requestBuilder = get("/posts/%s".formatted(mockId));

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

        verify(this.getPostService).getPost(mockId);
    }

    @Test
    @DisplayName("게시글 상세 조회(id로 post를 찾지 못한 경우 PostNotFound 에러 발생) - GET /posts/{id}")
    void testGetPostIfNotExists() throws Exception {
        String mockId = UUID.randomUUID().toString();

        given(this.getPostService.getPost(any())).willThrow(PostNotFound.class);

        RequestBuilder requestBuilder = get("/posts/%s".formatted(mockId));

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound());

        verify(this.getPostService).getPost(mockId);
    }

    @Test
    @DisplayName("게시글 수정 - PATCH /posts/{id}")
    void testUpdatePost() throws Exception {
        String mockId = UUID.randomUUID().toString();
        String mockNewTitle = "title2";
        String mockNewContent = "content2";

        String updatePostRequestData = """
                {
                    "title": "%s",
                    "content": "%s"
                }
                """.formatted(mockNewTitle, mockNewContent);

        RequestBuilder requestBuilder = patch("/posts/%s".formatted(mockId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatePostRequestData);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent());

        verify(this.updatePostService).updatePost(any(), any());
    }

    @Test
    @DisplayName("게시글 삭제 - DELETE /posts/{id}")
    void testDeletePost() throws Exception {
        String mockId = UUID.randomUUID().toString();

        RequestBuilder requestBuilder = delete("/posts/%s".formatted(mockId));

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent());

        verify(this.deletePostService).deletePost(mockId);
    }
}
