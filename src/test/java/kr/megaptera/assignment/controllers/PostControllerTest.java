package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CreatePostService;
import kr.megaptera.assignment.application.DeletePostService;
import kr.megaptera.assignment.application.GetPostService;
import kr.megaptera.assignment.application.GetPostsService;
import kr.megaptera.assignment.application.UpdatePostService;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.request.RqCreatePostDto;
import kr.megaptera.assignment.models.Author;
import kr.megaptera.assignment.models.Content;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.Title;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CreatePostService createPostService;

    @MockBean
    private DeletePostService deletePostService;

    @MockBean
    private GetPostService getPostService;

    @MockBean
    private GetPostsService getPostsService;

    @MockBean
    private UpdatePostService updatePostService;

    @Test
    void getPostList() throws Exception {

        PostId postId = new PostId("1");
        Title title = new Title("title");
        Author author = new Author("author");
        Content content = new Content("content");

        PostId postId2 = new PostId("2");
        Title title2 = new Title("title2");
        Author author2 = new Author("author2");
        Content content2 = new Content("content2");

        Post post = new Post(postId, title, author, content);
        Post post2 = new Post(postId2, title2, author2, content2);


        given(getPostsService.getPostList())
                .willReturn(new ResponseEntity<>(List.of(
                        new PostDto(post),
                        new PostDto(post2)),
                        HttpStatus.OK));

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("title")));
    }

    @Test
    void getPostById() throws Exception {

        PostId postId = new PostId("1");
        Title title = new Title("title");
        Author author = new Author("author");
        Content content = new Content("content");

        Post post = new Post(postId, title, author, content);

        given(getPostService.getPostById(eq("1")))
                .willReturn(new ResponseEntity<>(new PostDto(post), HttpStatus.OK));

        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("title")));
    }

    @Test
    void createPost() throws Exception {

        String inputJson = """
                {
                    "title": "title",
                    "author": "author",
                    "content": "content"
                }
                """;

        mockMvc.perform(post("/posts")
                .contentType("application/json")
                .content(inputJson))
                .andExpect(status().isOk());

        verify(createPostService).createPost(any(RqCreatePostDto.class));
    }
}