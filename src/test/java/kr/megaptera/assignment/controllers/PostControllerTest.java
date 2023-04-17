package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CreatePostService;
import kr.megaptera.assignment.application.DeletePostService;
import kr.megaptera.assignment.application.GetPostService;
import kr.megaptera.assignment.application.GetPostsService;
import kr.megaptera.assignment.application.UpdatePostService;
import kr.megaptera.assignment.dtos.PostCreateRequestDto;
import kr.megaptera.assignment.dtos.PostResponseDto;
import kr.megaptera.assignment.dtos.PostUpdateRequestDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
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

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {
  private static final int SETUP_ITEM_NUM = 5;
  @Autowired
  private MockMvc mockMvc;

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
  void list() throws Exception {
    given(getPostsService.getPosts()).willReturn(List.of(
        new PostResponseDto("001", "제목1", "작성자1", "내용1"),
        new PostResponseDto("002", "제목2", "작성자2", "내용2")
    ));

    mockMvc.perform(get("/posts"))
           .andExpect(status().isOk())
           .andExpect(content().string(
               containsString("제목1")
           ));
  }

  @Test
  @DisplayName("GET /posts/{id} - with correct ID")
  void detailWithCorrectId() throws Exception {
    // given
    String id = "123";

    given(getPostService.getPostDetail(id))
        .willReturn(new PostResponseDto(id, "제목", "작성자", "내용"));

    mockMvc.perform(get("/posts/" + id))
           .andExpect(status().isOk())
           .andExpect(content().string(
               containsString("제목")
           ));
  }

  @Test
  @DisplayName("GET /posts/{id} - with incorrect ID")
  void detail() throws Exception {
    String id = "999";

    given(getPostService.getPostDetail(id))
        .willThrow(new PostNotFound());

    mockMvc.perform(get("/posts/" + id))
           .andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("POST /posts")
  void create() throws Exception {
    String json = """
        {
          "title": "데브로드",
          "author": "홀맨",
          "content": "열심히 합시다"
        }
        """;

    mockMvc.perform(post("/posts")
               .contentType(MediaType.APPLICATION_JSON)
               .content(json)
           )
           .andExpect(status().isCreated());

    verify(createPostService).createPost(any(PostCreateRequestDto.class));
  }

  @Test
  @DisplayName("PATCH /posts/{id}")
  void update() throws Exception {
    String id = "001POST";

    String json = """
        {
          "title": "데브로드",
          "content": "열심히 합시다"
        }
        """;

    mockMvc.perform(patch("/posts/" + id)
               .contentType(MediaType.APPLICATION_JSON)
               .content(json)
           )
           .andExpect(status().isNoContent());

    verify(updatePostService)
        .updatePost(eq(id), any(PostUpdateRequestDto.class));
  }

  @Test
  @DisplayName("DELETE /posts/{id}")
  void deletePost() throws Exception {
    String id = "001POST";

    mockMvc.perform(delete("/posts/" + id))
           .andExpect(status().isNoContent());

    verify(deletePostService).deletePost(id);
  }
}
